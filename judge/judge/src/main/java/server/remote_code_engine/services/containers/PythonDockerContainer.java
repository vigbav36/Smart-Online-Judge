package server.remote_code_engine.services.containers;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.WaitContainerResultCallback;
import okhttp3.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.springframework.stereotype.Service;
import server.remote_code_engine.services.containers.docker.DockerClientHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class PythonDockerContainer implements ContainerManager{

    private final DockerClientHelper dockerClientHelper = new DockerClientHelper();
    private final DockerClient dockerClient = dockerClientHelper.getClient();

    @Override
    public String createContainer(String imageName, String containerId, String code, String testCases) {
        try {
            String[] envVariables = new String[]{ "USER_CODE=" + code, "TESTCASES_JSON=" + testCases };

            CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                    .withName("SUBMISSION_"+containerId)
                    .withEnv(envVariables)
                    .exec();

            return container.getId();
        }
        catch (Exception e){
            e.toString();
            return null;
        }
    }

    @Override
    public String startContainer(String containerId) throws Exception {
        dockerClient.startContainerCmd(containerId).exec();
        return containerId;
    }

    @Override
    public String getContainerLogs(String containerId) throws Exception {
       return "None";
    }

    @Override
    public void removeContainer(String containerId) throws Exception {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void executeCodeInContainerAsync(String imageName, String containerId, String code, String testCases) {
        CompletableFuture.runAsync(() -> {
            try {
                String createdContainerId = createContainer(imageName, containerId, code, testCases);
                if (createdContainerId == null) {
                    System.err.println("Failed to create container");
                    return;
                }

                dockerClient.startContainerCmd(createdContainerId).exec();
                System.out.println("Container started asynchronously: " + createdContainerId);


                dockerClient.waitContainerCmd(createdContainerId).exec(new WaitContainerResultCallback() {
                    @Override
                    public void onComplete() {
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        try {
                            try (TarArchiveInputStream tarStream = new TarArchiveInputStream(
                                    dockerClient.copyArchiveFromContainerCmd(createdContainerId, "/usr/src/app/output.json").exec())) {

                                TarArchiveEntry entry;
                                while ((entry = tarStream.getNextTarEntry()) != null) {
                                    if (entry.getName().equals("output.json")) {
                                        byte[] buffer = new byte[1024];
                                        int bytesRead;
                                        while ((bytesRead = tarStream.read(buffer)) != -1) {
                                            outputStream.write(buffer, 0, bytesRead);
                                        }
                                        break;
                                    }
                                }
                            }

                            String result = outputStream.toString("UTF-8");
                            sendWebhookNotification(containerId, result);
                            System.out.println("Container execution completed. Output: " + result);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void sendWebhookNotification(String containerId, String result) {
        String webhookUrl = "http://localhost:8000/webhooks/submissions/" + containerId;
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(result, JSON);

        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("Webhook response: " + response.body().string());
                } else {
                    System.err.println("Webhook call failed: " + response.message());
                }
            }
        });
    }
}

package server.remote_code_engine.services.containers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Service;

import java.time.Duration;


public class DockerClientHelper {
    DockerClientConfig config;
    DockerHttpClient httpClient;
    public DockerClientHelper() {
        this.config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        this.httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
    }
    public DockerClient getClient(){
        return DockerClientImpl.getInstance(this.config, this.httpClient);
    }
}

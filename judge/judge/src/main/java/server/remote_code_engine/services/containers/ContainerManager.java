package server.remote_code_engine.services.containers;

public interface ContainerManager {
    String createContainer(String imageName, String containerId, String code, String testCases) throws Exception;
    String startContainer(String containerId) throws Exception;
    String getContainerLogs(String containerId) throws Exception;
    void removeContainer(String containerId) throws Exception;
}

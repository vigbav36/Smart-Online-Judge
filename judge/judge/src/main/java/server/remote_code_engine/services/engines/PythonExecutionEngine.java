package server.remote_code_engine.services.engines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.remote_code_engine.models.SubmissionMessage;
import server.remote_code_engine.services.containers.PythonDockerContainer;

@Service
public class PythonExecutionEngine implements ExecutionEngine{
    @Autowired
    private PythonDockerContainer dockerContainer;

    @Override
    public void execute(SubmissionMessage  message) throws Exception {
        dockerContainer.executeCodeInContainerAsync("python-code-executer", message.getSubmissionId(), message.getCode(), message.getTestCases());
    }
}

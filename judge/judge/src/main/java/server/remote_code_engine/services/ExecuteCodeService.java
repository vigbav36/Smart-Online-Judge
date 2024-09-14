package server.remote_code_engine.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.remote_code_engine.models.SubmissionMessage;
import server.remote_code_engine.services.engines.PythonExecutionEngine;

@Service
@Slf4j
public class ExecuteCodeService {

    @Autowired
    private PythonExecutionEngine pythonExecutionEngine;

    @Autowired
    private ObjectMapper objectMapper;

    public void readSubmission(String submissionMessage) throws Exception {

    }

    public void executeCode(SubmissionMessage  submission) throws Exception {
        pythonExecutionEngine.execute(submission);
    }
}

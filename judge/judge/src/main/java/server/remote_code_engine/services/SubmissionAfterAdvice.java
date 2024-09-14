package server.remote_code_engine.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.remote_code_engine.models.SubmissionMessage;

@Aspect
@Component
public class SubmissionAfterAdvice {
    @Autowired
    private ExecuteCodeService executeCodeService;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterReturning(
            pointcut = "execution(* server.remote_code_engine.services.ExecuteCodeService.readSubmission(..)) && args(submissionMessage)"
    )
    public void afterReadSubmission(String submissionMessage) {
        try {
            SubmissionMessage submission = objectMapper.readValue(submissionMessage, SubmissionMessage.class);
            executeCodeService.executeCode(submission);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



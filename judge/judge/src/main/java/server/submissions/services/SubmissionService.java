package server.submissions.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.submissions.dao.SubmissionDAO;
import server.submissions.models.Submission;
import server.submissions.models.SubmissionMessage;
import server.submissions.models.SubmissionResult;
import server.testcases.dao.TestCaseDAO;
import server.testcases.models.Testcase;

import java.util.ArrayList;
import java.util.Base64;


import java.util.List;

@Service
public class SubmissionService {

    private final ObjectMapper objectMapper;

    @Autowired
    private SubmissionMessageService submissionMessageService;

    @Autowired
    private SubmissionDAO submissionDAO;

    @Autowired
    private TestCaseDAO testCaseDAO;

    public SubmissionService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convertMessageModelToString(SubmissionMessage message){
        try {
            return objectMapper.writeValueAsString(message);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public Submission addSubmission(Submission submission) throws JsonProcessingException {
        submission = submissionDAO.addSubmission(submission);

        SubmissionMessage submissionMessage = submission.convertToSubmissionMessage();
        List<Testcase> testcases = testCaseDAO.getTestcasesByQuestionId(submission.getQuestionId());
        String testCases = objectMapper.writeValueAsString(testcases);
        String encodedtestCases = Base64.getEncoder().encodeToString(testCases.getBytes());
        submissionMessage.setTestCases(encodedtestCases);

        submissionMessageService.pushSubmission(convertMessageModelToString(submissionMessage));

        return submission;
    }

    public Submission getSubmission(Long submissionId){
        return submissionDAO.getSubmission(submissionId);
    }
}

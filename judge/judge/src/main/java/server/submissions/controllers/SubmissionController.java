package server.submissions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.submissions.models.Submission;
import server.submissions.models.SubmissionMessage;
import server.submissions.models.SubmissionResult;
import server.submissions.services.SubmissionMessageService;
import server.submissions.services.SubmissionResultService;
import server.submissions.services.SubmissionService;

import java.util.List;


@RequestMapping("/api/submissions")
@RestController
public class SubmissionController {

    @Autowired
    private SubmissionMessageService submissionMessageService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionResultService submissionResultService;

    @PostMapping("/produce")
    public String produceMessage(@RequestBody SubmissionMessage message) {
        String messageConverted = submissionService.convertMessageModelToString(message);
        return submissionMessageService.pushSubmission(messageConverted);
    }

    @PostMapping("")
    public ResponseEntity<Submission> submit(@RequestBody Submission submission) {
        try {
            Submission createdSubmission = submissionService.addSubmission(submission);
            return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long submissionId) {
        try {
            Submission createdSubmission = submissionService.getSubmission(submissionId);
            return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{submissionId}/results")
    public ResponseEntity<List<SubmissionResult>> getSubmissionResults(@PathVariable Long submissionId) {
        try{
            List<SubmissionResult> results = submissionResultService.getSubmissionResults(submissionId);
            return new ResponseEntity<>(results, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
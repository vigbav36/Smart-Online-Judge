package server.submissions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.submissions.models.SubmissionResult;
import server.submissions.services.SubmissionResultService;

import java.nio.file.Path;
import java.util.List;

@RequestMapping("/webhooks/submissions")
@RestController
public class SubmissionWebhook {

    @Autowired
    private SubmissionResultService submissionResultService;
    @PostMapping("/{submissionId}")
    public void updateResults(@RequestBody List<SubmissionResult> results, @PathVariable Long submissionId){
        submissionResultService.addOrUpdateSubmissionResults(results, submissionId);
    }
}

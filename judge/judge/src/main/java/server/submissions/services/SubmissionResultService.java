package server.submissions.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.submissions.dao.SubmissionDAO;
import server.submissions.dao.SubmissionResultsDAO;
import server.submissions.models.SubmissionResult;

import java.util.List;

@Service
public class SubmissionResultService {

    @Autowired
    private SubmissionDAO submissionDAO;

    @Autowired
    private SubmissionResultsDAO submissionResultsDAO;

    public List<SubmissionResult> getSubmissionResults( Long submissionId){
        return submissionResultsDAO.getResultsWithSubmissionId(submissionId);
    }
    public void addOrUpdateSubmissionResults(List<SubmissionResult> results, Long submissionId){
        for(SubmissionResult result : results)
            result.setSubmissionId(submissionId);
        submissionResultsDAO.addOrUpdateSubmissionResults(results);
    }
}

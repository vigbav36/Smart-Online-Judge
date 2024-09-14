package server.submissions.dao;

import com.jooq.generated.tables.records.SubmissionResultRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.submissions.models.Status;
import server.submissions.models.SubmissionResult;

import java.util.List;
import java.util.stream.Collectors;

import static com.jooq.generated.tables.SubmissionResult.SUBMISSION_RESULT;

@Repository
@RequiredArgsConstructor
public class SubmissionResultsDAO {

    @Autowired
    private DSLContext dslContext;


    public void addResultsForSubmission(List<SubmissionResult> results){
        List<SubmissionResultRecord> records = results.stream()
                .map(result -> {
                  SubmissionResultRecord record = dslContext.newRecord(SUBMISSION_RESULT);
                  record.setSubmissionId(result.getSubmissionId());
                  record.setTestcaseId(result.getTestcaseId());
                  record.setStatus(result.getStatus().name());
                  record.setOutput(result.getOutput());
                  record.setTimeTaken(result.getTimeTaken());
                  record.setMemoryUsed(result.getMemoryUsed());
                  record.setMessage(result.getMessage());
                  return record;
                }).toList();
        dslContext.batchInsert(records).execute();
    }


    public void updateResultsForSubmission(List<SubmissionResult> results) {

        List<SubmissionResultRecord> records = results.stream()
                .map(result -> {
                    SubmissionResultRecord record = dslContext.newRecord(SUBMISSION_RESULT);

                    record.setSubmissionId(result.getSubmissionId());
                    record.setTestcaseId(result.getTestcaseId());

                    record.setStatus(result.getStatus().name());
                    record.setOutput(result.getOutput());
                    record.setTimeTaken(result.getTimeTaken());
                    record.setMemoryUsed(result.getMemoryUsed());
                    record.setMessage(result.getMessage());

                    record.changed(SUBMISSION_RESULT.STATUS, true);
                    record.changed(SUBMISSION_RESULT.OUTPUT, true);
                    record.changed(SUBMISSION_RESULT.TIME_TAKEN, true);
                    record.changed(SUBMISSION_RESULT.MEMORY_USED, true);
                    record.changed(SUBMISSION_RESULT.MESSAGE, true);

                    return record;
                })
                .toList();

        // Perform batch update
        dslContext.batchUpdate(records).execute();
    }

    public void addOrUpdateSubmissionResults(List<SubmissionResult> results){
        if(checkIfSubmissionExists(results)){
            updateResultsForSubmission(results);
        }
        else
            addResultsForSubmission(results);
    }
    public boolean checkIfSubmissionExists(List<SubmissionResult> results){
      return dslContext
                .fetchExists(
                        dslContext.selectFrom(SUBMISSION_RESULT)
                                .where(SUBMISSION_RESULT.SUBMISSION_ID.eq(results.get(0).getSubmissionId()))

                );
    }
    public List<SubmissionResult> getResultsWithSubmissionId(Long submissionId) {
        Result<Record> records = dslContext
                .select()
                .from(SUBMISSION_RESULT)
                .where(SUBMISSION_RESULT.SUBMISSION_ID.eq(submissionId))
                .fetch();

        return records.stream()
                .map(record -> new SubmissionResult(
                        record.get(SUBMISSION_RESULT.SUBMISSION_ID),
                        record.get(SUBMISSION_RESULT.TESTCASE_ID),
                        Status.valueOf(record.get(SUBMISSION_RESULT.STATUS)),
                        record.get(SUBMISSION_RESULT.OUTPUT),
                        record.get(SUBMISSION_RESULT.TIME_TAKEN),
                        record.get(SUBMISSION_RESULT.MEMORY_USED),
                        record.get(SUBMISSION_RESULT.MESSAGE)
                ))
                .collect(Collectors.toList());
    }
}

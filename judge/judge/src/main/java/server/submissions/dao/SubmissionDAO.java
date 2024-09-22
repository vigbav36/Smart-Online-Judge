package server.submissions.dao;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.QOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.submissions.models.Status;
import server.submissions.models.Submission;
import org.jooq.Record;

import static com.jooq.generated.tables.Submission.SUBMISSION;
import static server.submissions.models.Status.PENDING;

@Repository
@RequiredArgsConstructor
public class SubmissionDAO {
    @Autowired
    private final DSLContext dslContext;


    public Submission addSubmission(Submission submission){
        Record submissionRecord = dslContext.insertInto(SUBMISSION)
                .columns(SUBMISSION.QUESTION_ID, SUBMISSION.LANGUAGE, SUBMISSION.SUBMITTED_CODE, SUBMISSION.STATUS)
                .values(submission.getQuestionId(), submission.getLanguage().name(), submission.getSubmittedCode(), PENDING.name())
                .returning(SUBMISSION.SUBMISSION_ID)
                .fetchOne();
        return getSubmission(submissionRecord.getValue(SUBMISSION.SUBMISSION_ID));
    }


    public void updateSubmissionStatus(Long submissionId, Status status){
        dslContext.update(SUBMISSION)
                .set(SUBMISSION.STATUS, status.name())
                .where(SUBMISSION.SUBMISSION_ID.eq(submissionId))
                .execute();
    }

    public Submission getSubmission(Long submissionId){
        return dslContext.select(SUBMISSION.SUBMISSION_ID, SUBMISSION.QUESTION_ID, SUBMISSION.LANGUAGE, SUBMISSION.SUBMITTED_CODE, SUBMISSION.STATUS, SUBMISSION.SUBMISSION_TIME)
                .from(SUBMISSION)
                .where(SUBMISSION.SUBMISSION_ID.eq(submissionId))
                .fetchOneInto(Submission.class);
    }
}

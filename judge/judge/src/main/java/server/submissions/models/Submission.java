package server.submissions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Base64;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Submission {
    private Long submissionId;
    private Long questionId;
    private Language language;
    private String submittedCode;
    private Status status;
    private Timestamp submissionTime;

    public SubmissionMessage convertToSubmissionMessage(){
        SubmissionMessage submissionMessage = new SubmissionMessage();
        submissionMessage.setSubmissionId(String.valueOf(this.submissionId));
        submissionMessage.setCode(Base64.getEncoder().encodeToString(this.submittedCode.getBytes()));
        submissionMessage.setLanguage(this.language);
        return submissionMessage;
    }
}

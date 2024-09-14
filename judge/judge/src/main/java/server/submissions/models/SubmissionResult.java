package server.submissions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmissionResult {
    private Long submissionId;
    private Long testcaseId;
    private Status status;
    private String output;
    private Long timeTaken;
    private Long memoryUsed;
    private String message;
}

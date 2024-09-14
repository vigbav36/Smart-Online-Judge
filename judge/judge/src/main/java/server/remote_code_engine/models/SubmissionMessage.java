package server.remote_code_engine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SubmissionMessage {
    private String code;
    private String testCases;
    private String submissionId;
    private Language language;
}

package server.testcases.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Testcase {
    private Long testcaseId;
    private Long questionId;
    private String input;
    private String expectedOutput;
    private Long timeLimit;
    private Long memoryLimit;
    private Boolean isDeleted;
}

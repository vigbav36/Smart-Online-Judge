package server.questions.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.questions.models.Question;
import server.questions.services.QuestionService;
import server.testcases.models.Testcase;
import server.testcases.services.TestcaseService;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestcaseService testcaseService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question createdQuestion =  questionService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }
    @GetMapping(path="/{questionId}"  ,produces = "application/json")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        Question question =  questionService.getQuestionWithId(questionId);
        if(question != null)
            return new ResponseEntity<>(question, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updatedQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    @PostMapping(path="/{questionId}/add-testcases", produces = "application/json")
    public ResponseEntity<List<Testcase>> addTestCases(@PathVariable Long questionId,
                                                       @RequestBody List<Testcase> testcases){
        List<Testcase> testCases = testcaseService.addTestCases(questionId, testcases);
        return new ResponseEntity<>(testCases, HttpStatus.OK);
    }

    @GetMapping(path="/{questionId}/get-testcases", produces = "application/json")
    public ResponseEntity<List<Testcase>> getTestCases(@PathVariable Long questionId){
        List<Testcase> testCases = testcaseService.getTestCases(questionId);
        return new ResponseEntity<>(testCases, HttpStatus.OK);
    }
}



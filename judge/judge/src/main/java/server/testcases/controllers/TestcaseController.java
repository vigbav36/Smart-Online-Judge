package server.testcases.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.testcases.models.Testcase;
import server.testcases.services.TestcaseService;

@RestController
@RequestMapping("/api/testcase")
public class TestcaseController {

    @Autowired
    private TestcaseService testcaseService;

}

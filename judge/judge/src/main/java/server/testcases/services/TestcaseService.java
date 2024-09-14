package server.testcases.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.testcases.dao.TestCaseDAO;
import server.testcases.models.Testcase;

import java.util.List;

@Service
public class TestcaseService {
    @Autowired
    private TestCaseDAO testCaseDAO;


    public Testcase addTestCase(Long questionId, Testcase testcase){
        return testCaseDAO.addTestcase(questionId, testcase);
    }

    public List<Testcase> addTestCases(Long questionId, List<Testcase> testcases){
        return testCaseDAO.addTestcases(questionId, testcases);
    }

    public List<Testcase> getTestCases(Long questionId){
        return testCaseDAO.getTestcasesByQuestionId(questionId);
    }
}

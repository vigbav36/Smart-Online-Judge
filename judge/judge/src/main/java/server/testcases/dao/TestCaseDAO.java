package server.testcases.dao;

import com.jooq.generated.tables.records.TestcaseRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.testcases.models.Testcase;

import java.util.List;
import java.util.stream.Collectors;

import static com.jooq.generated.tables.Testcase.TESTCASE;
@Repository
@RequiredArgsConstructor
public class TestCaseDAO {
    @Autowired
    private final DSLContext dslContext;

    public Testcase addTestcase(Long questionId, Testcase testcase){
        Record record = dslContext.insertInto(TESTCASE)
                .columns(TESTCASE.QUESTION_ID, TESTCASE.INPUT, TESTCASE.EXPECTED_OUTPUT, TESTCASE.TIME_LIMIT,
                        TESTCASE.MEMORY_LIMIT, TESTCASE.IS_DELETED)
                .values(testcase.getQuestionId(), testcase.getInput(), testcase.getExpectedOutput(), testcase.getTimeLimit(),
                        testcase.getMemoryLimit(), testcase.getIsDeleted())
                .returning(TESTCASE.TESTCASE_ID)
                .fetchOne();
        Long id =  record.getValue(TESTCASE.TESTCASE_ID, Long.class);
        return readTestCase(id);
    }

    public Testcase readTestCase(Long testCaseId){
        return dslContext.select(TESTCASE.TESTCASE_ID, TESTCASE.QUESTION_ID, TESTCASE.INPUT, TESTCASE.EXPECTED_OUTPUT, TESTCASE.TIME_LIMIT,
                TESTCASE.MEMORY_LIMIT, TESTCASE.IS_DELETED)
                .from(TESTCASE)
                .fetchOneInto(Testcase.class);
    }

    public List<Testcase> addTestcases(Long questionId, List<Testcase> testcases) {
        // Set the questionId for each testcase
        for (Testcase testcase : testcases) {
            testcase.setQuestionId(questionId);
        }

        List<TestcaseRecord> records = testcases.stream()
                .map(testcase -> {
                    TestcaseRecord record = dslContext.newRecord(TESTCASE);
                    record.setQuestionId(testcase.getQuestionId());
                    record.setInput(testcase.getInput());
                    record.setExpectedOutput(testcase.getExpectedOutput());
                    record.setTimeLimit(testcase.getTimeLimit());
                    record.setMemoryLimit(testcase.getMemoryLimit());
                    record.setIsDeleted(false);
                    return record;
                }).collect(Collectors.toList());

        // Perform the batch insert
        dslContext.batchInsert(records).execute();

        return getTestcasesByQuestionId(questionId);
    }

    public List<Testcase> getTestcasesByQuestionId(Long questionId) {
        return dslContext.selectFrom(TESTCASE)
                .where(TESTCASE.QUESTION_ID.eq(questionId))
                .and(TESTCASE.IS_DELETED.eq(false))
                .fetchInto(Testcase.class);
    }
}

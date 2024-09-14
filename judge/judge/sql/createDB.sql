
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


create table Question (
    question_id BIGSERIAL PRIMARY KEY ,
    title TEXT,
    description TEXT,
    boiler_plate_code TEXT
);

create table Testcase (
    testcase_id BIGSERIAL PRIMARY KEY,
    question_id BIGINT,
    input TEXT,
    expected_output TEXT,
    time_limit BIGINT,
    memory_limit BIGINT,
    is_deleted BOOLEAN,
    constraint fk_questionid_question foreign key(question_id) references question(question_id)
);

CREATE TABLE Submission (
    submission_id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    language VARCHAR(50) NOT NULL,
    submitted_code TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    submission_time TIMESTAMP default now() NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

CREATE TABLE submission_result (
    result_id BIGSERIAL PRIMARY KEY,
    submission_id BIGINT NOT NULL,
    testcase_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    output TEXT,
    message TEXT,
    time_taken BIGINT,
    memory_used BIGINT,
    FOREIGN KEY (testcase_id) REFERENCES Testcase(testcase_id),
    FOREIGN KEY (submission_id) REFERENCES Submission(submission_id)
);


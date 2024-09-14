package server.remote_code_engine.services.engines;

import server.remote_code_engine.models.SubmissionMessage;

public interface ExecutionEngine {
    void  execute(SubmissionMessage message) throws Exception;
}

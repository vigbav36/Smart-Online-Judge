import subprocess
import time
import psutil
import sys
import os
import json
import resource
from signal import SIGINT, signal

def set_limits(time_limit, memory_limit_kb):
    resource.setrlimit(resource.RLIMIT_CPU, (time_limit, time_limit))
    memory_limit_bytes = memory_limit_kb * 1024
    resource.setrlimit(resource.RLIMIT_AS, (memory_limit_bytes, memory_limit_bytes))

def run_user_code(file_path, input_data, time_limit, memory_limit_kb):
    def handle_timeout(signum, frame):
        raise TimeoutError("Process timed out")
    
    signal(SIGINT, handle_timeout)
    
    try:
        process = psutil.Popen(
            [sys.executable, file_path],
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            preexec_fn=lambda: set_limits(time_limit, memory_limit_kb)
        )
        
        start_time = time.time()
        try:
            output, error = process.communicate(input=input_data.strip(), timeout=time_limit)
            end_time = time.time()
        except subprocess.TimeoutExpired:
            process.kill()
            output, error = process.communicate()
            return "Time Limit Exceeded", "Process timed out", 0
        
        time_taken = int((end_time - start_time) * 1000)
        
        if error:
            return "Runtime Error", error.strip(), time_taken
        
        return output.strip(), None, time_taken
    
    except MemoryError:
        return "Memory Limit Exceeded", "Memory limit exceeded", 0
    except Exception as e:
        return "Runtime Error", str(e), 0

def run_test_case(testcase):

    result = {
        "test_case_id": testcase.get('testcase_id'),
        "status": "FAILED",
        "message": "",
        "time_taken": 0,
        "output": ""
    }

    try:
        output, error, time_taken = run_user_code(
            "user_code.py",
            testcase.get('input'),
            testcase.get('time_limit'),
            testcase.get('memory_limit')
        )
        result['output'] = output
        if error:
            result["message"] = error
            if "timed out" in error:
                result["status"] = "TIME_LIMIT_EXCEEDED"
            elif "Memory limit exceeded" in error:
                result["status"] = "MEMORY_LIMIT_EXCEEDED"
            else:
                result["status"] = "RUNTIME_ERROR"
        else:
            if output.strip() == testcase.get('expected_output').strip():
                result["status"] = "PASSED"
            else:
                result["status"] = "WRONG_ANSWER"
                result["message"] = f"Expected: '{testcase.get('expected_output')}', Got: '{output}'"

        result["time_taken"] = time_taken
    except Exception as e:
        result["message"] = f"An unexpected error occurred: {str(e)}"
        result["status"] = "Runtime Error"
    
    return result

def run_all_tests():

    
    testcases = json.load(open('test_case.json'))
    
    results = []
    for testcase in testcases:
        result = run_test_case(testcase)
        results.append(result)
    
    with open('output.json', 'w') as f:
        json.dump(results, f, indent=4)
        
    if any(result["status"] != "Passed" for result in results):
        sys.exit(1)  
    sys.exit(0)


run_all_tests()


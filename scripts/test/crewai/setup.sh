#!/bin/sh
echo "$TESTCASES_JSON" > /usr/src/app/test_case.json
echo "$USER_CODE" > /usr/src/app/user_code.py
exec python test_runner.py
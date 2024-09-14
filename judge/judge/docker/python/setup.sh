#!/bin/sh

echo "$TESTCASES_JSON" | base64 -d > /usr/src/app/test_case.json
echo "$USER_CODE" | base64 -d > /usr/src/app/user_code.py

exec python test_runner.py
import React, { useState } from 'react';
import { Modal, Button, Table } from 'antd';
import '../styles/SubmissionResults.css'

const SubmissionResult = ({setSubmissionResults ,subissionResults}) => {
  const data = [
    {
      submissionId: 38,
      testcaseId: 8,
      status: "WRONG_ANSWER",
      output: "['2 4 6']",
      timeTaken: 34,
      memoryUsed: 200,
      message: "Expected: '2 4 6', Got: '['2 4 6']'"
    },
    {
        submissionId: 38,
        testcaseId: 8,
        status: "WRONG_ANSWER",
        output: "['2 4 6']",
        timeTaken: 34,
        memoryUsed: 200,
        message: "Expected: '2 4 6', Got: '['2 4 6']'"
      },
      {
        submissionId: 38,
        testcaseId: 8,
        status: "WRONG_ANSWER",
        output: "['2 4 6']",
        timeTaken: 34,
        memoryUsed: 200,
        message: "Expected: '2 4 6', Got: '['2 4 6']'"
      },
      {
        submissionId: 38,
        testcaseId: 8,
        status: "WRONG_ANSWER",
        output: "['2 4 6']",
        timeTaken: 34,
        memoryUsed: 200,
        message: "Expected: '2 4 6', Got: '['2 4 6']'"
      },
      {
        submissionId: 38,
        testcaseId: 8,
        status: "WRONG_ANSWER",
        output: "['2 4 6']",
        timeTaken: 34,
        memoryUsed: 200,
        message: "Expected: '2 4 6', Got: '['2 4 6']'"
      },
      {
        submissionId: 38,
        testcaseId: 8,
        status: "WRONG_ANSWER",
        output: "['2 4 6']",
        timeTaken: 34,
        memoryUsed: 200,
        message: "Expected: '2 4 6', Got: '['2 4 6']'"
      },
  ];

  const columns = [
    {
      title: 'Test Case ID',
      dataIndex: 'testcaseId',
      key: 'testcaseId',
    },
    {
      title: 'Status',
      dataIndex: 'status',
      key: 'status',
    },
    {
      title: 'Output',
      dataIndex: 'output',
      key: 'output',
    },
    {
      title: 'Message',
      dataIndex: 'message',
      key: 'message',
    },
    {
        title: 'Time Taken (ms)',
        dataIndex: 'timeTaken',
        key: 'timeTaken',
        },
        {
        title: 'Memory Used (KB)',
        dataIndex: 'memoryUsed',
        key: 'memoryUsed',
        },
  ];

  return (
    <div>
        <Table
            dataSource={subissionResults}
            columns={columns}
            pagination={false} 
            rowKey="submissionId"
        />
    </div>
  );
};

export default SubmissionResult;
import React, { useState } from 'react';
import { Modal, Button, Table } from 'antd';

const SubmissionModal = () => {
  const [isModalVisible, setIsModalVisible] = useState(true);

  const data = [
    {
      submissionId: 38,
      testcaseId: 8,
      status: "WRONG_ANSWER",
      output: "['2 4 6']",
      timeTaken: 34,
      memoryUsed: 200,
      message: "Expected: '2 4 6', Got: '['2 4 6']'"
    }
  ];

  const columns = [
    {
      title: 'Submission ID',
      dataIndex: 'submissionId',
      key: 'submissionId',
    },
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
      title: 'Time Taken (ms)',
      dataIndex: 'timeTaken',
      key: 'timeTaken',
    },
    {
      title: 'Memory Used (KB)',
      dataIndex: 'memoryUsed',
      key: 'memoryUsed',
    },
    {
      title: 'Message',
      dataIndex: 'message',
      key: 'message',
    }
  ];

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <div>
      <Button type="primary" onClick={showModal}>
        Show Submission Results
      </Button>
      <Modal
        title="Submission Results"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        width={"75%"}
        centered
      >
        <Table
          dataSource={data}
          columns={columns}
          pagination={false}  // Disable pagination since we have only one row
          rowKey="submissionId"
        />
      </Modal>
    </div>
  );
};

export default SubmissionModal;

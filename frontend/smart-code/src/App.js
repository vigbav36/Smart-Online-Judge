import React, { useState } from 'react';
import { Row, Col } from 'antd';
import LeftPane from './components/LeftPane';
import RightPane from './components/RightPane';
import {Layout } from 'antd';
import PageHeader from './components/PageHeader';
import './App.css'
import SubmissionModal from './components/SubmissionModal';

const App = () => {
  const [subissionResults , setSubmissionResults] = useState([])
  const [activeTab, setActiveTab] = useState('1');
  return (
    <Layout>
      <PageHeader />
      <Row style={{ height: '100vh' }}>
        <Col span={12}>
          <LeftPane setSubmissionResults={setSubmissionResults} subissionResults={subissionResults} activeTab={activeTab} setActiveTab={setActiveTab}/>
        </Col>
        <Col span={12}>
          <RightPane setSubmissionResults={setSubmissionResults} subissionResults={subissionResults} activeTab={activeTab} setActiveTab={setActiveTab}/>
        </Col>
      </Row>
    </Layout>
  );
};

export default App;
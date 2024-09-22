import React, {useState} from 'react';
import Question from './Question';
import AudioListener from './AudioListener';
import { Tabs } from 'antd';
import '../styles/LeftPane.css'
import SubmissionResult from './SubmissionResults';

const { TabPane } = Tabs;


const LeftPane = ({setSubmissionResults ,subissionResults, activeTab, setActiveTab}) => {
    return (
        <div style={{ 
            width: '100%', 
            padding: '10px', 
            boxSizing: 'border-box', 
            display: 'flex', 
            flexDirection: 'column', 
            height: '100%', 
            backgroundColor: "#1e293b",
            color : "white"
        }}>
            <Tabs activeKey={activeTab} onChange={(key) => setActiveTab(key)}  defaultActiveKey="1" style={{ height: '100%'}}>
                <TabPane tab="Question" key="1" >
                    <div style={{ flex: 1 }}>
                        <Question />
                    </div>
                    <div style={{ flex: 1 }}>
                        <AudioListener />
                    </div>
                </TabPane>
                <TabPane tab="Submission" key="2">
                    <SubmissionResult setSubmissionResults={setSubmissionResults} subissionResults={subissionResults}/>
                </TabPane>
            </Tabs>
        </div>
    );
}

export default LeftPane;

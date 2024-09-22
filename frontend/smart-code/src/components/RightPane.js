import React, { useState } from 'react';
import { Row, Col} from 'antd';
import TextEditor from './TextEditor';
import RunButton from './runButton';


const RightPane = ({setSubmissionResults, subissionResults, setActiveTab}) => {
    const [code, setCode] = useState('');
    
    return (<>
       
        <div style={{ width: '100%', height: '100%', padding: '50px', boxSizing: 'border-box', backgroundColor: "#1e293b" }}>
            <TextEditor code={code} setCode={setCode} style={{ width: '100%', height: '100%' }} />
            <Row justify="center" style={{ marginTop: '10px' }}>
                <Col>
                    <RunButton code={code} setSubmissionResults={setSubmissionResults} setActiveTab={setActiveTab}/>
                </Col>
            </Row>
        </div>
        </>
    );
};

export default RightPane;

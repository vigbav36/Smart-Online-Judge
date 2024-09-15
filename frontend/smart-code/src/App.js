import React from 'react';
import TextEditor from './components/TextEditor';

const App = () => {
  return (
    <div style={{ display: 'flex', height: '100vh' }}>
      <div style={{ width: '50%', padding: '10px', boxSizing: 'border-box' }}>
        <h3>Question Description</h3>
        <p>This is where the question description goes.</p>

        <h3>Test Cases</h3>
        <p>Test case 1: ...</p>
        <p>Test case 2: ...</p>
      </div>

      <div style={{ width: '50%', padding: '10px', boxSizing: 'border-box', height: '75%' }}>
        <h3>Code Editor</h3>
        <TextEditor />
      </div>
    </div>
  );
};

export default App;
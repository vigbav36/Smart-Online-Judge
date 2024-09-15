import React, { useState, useCallback } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import { python } from '@codemirror/lang-python';

function TextEditor() {
  const [code, setCode] = useState("print('hello world!')");

  const onChange = useCallback((code, viewUpdate) => {
    setCode(code);
  }, []);

  return (
    <CodeMirror
      value={code}     
      height="75vh"
      onChange={onChange}
    />
  );
}

export default TextEditor;

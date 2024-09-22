import React, { useState, useCallback } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import {nord} from '@uiw/codemirror-theme-nord';


function TextEditor({code, setCode}) {
  const handleChange = useCallback((code, viewUpdate) => {
    console.log(code)
    setCode(code);
  }, []);

  return (
    <CodeMirror
      value={code}     
      height="75vh"
      theme={nord}
      onChange={handleChange}
    />
  );
}

export default TextEditor;

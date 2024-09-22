import React from 'react';
import SpeechRecognition, { useSpeechRecognition } from 'react-speech-recognition';
import '../styles/Audio.css'
import Waveform from './Waveform';

const AudioListener = () => {
  const {
    transcript,
    listening,
    resetTranscript,
    browserSupportsSpeechRecognition,
  } = useSpeechRecognition();

  const startListening = () => {
    navigator.mediaDevices.enumerateDevices()
    .then(devices => {
      const microphones = devices.filter(device => device.kind === 'audioinput');
      if (microphones.length === 0) {
        console.log("Microphone not available");
      } else {
        console.log(microphones); 
        SpeechRecognition.startListening();
      }
    })
    .catch(err => console.error('Error accessing media devices.', err));
  };

  if (!browserSupportsSpeechRecognition) {
    return <span>Browser doesn't support speech recognition.</span>;
  }
  
  return (
    <div className="centered-content">
      {/* <p>Microphone: {listening ? 'on' : 'off'}</p>
      <button onClick={startListening}>Start</button>
      <button onClick={SpeechRecognition.stopListening}>Stop</button>
      <button onClick={resetTranscript}>Reset</button>
      <p>transcript - {transcript}</p> */}
      {/* <Waveform /> */}
    </div>
  );
};
export default AudioListener;
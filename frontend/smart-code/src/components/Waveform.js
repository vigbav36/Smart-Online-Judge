import React from 'react';
import '../styles/Waveform.css'

const Waveform = () => {
    return (
        <div className='waveform-container'>
            <svg className="audiogram col" xmlns="http://www.w3.org/2000/svg" height="200">
                <defs>
                    <linearGradient id="audiogram-background" x1="0.5" y1="0" x2="0.5" y2="1">
                        <stop offset="0%" stopColor="#00e5d1" />
                        <stop offset="31.33%" stopColor="#13c3d2" />
                        <stop offset="94%" stopColor="#2577f9" />
                    </linearGradient>
                </defs>
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="102.2" x="0" y="49.9" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="135.0" x="20" y="33.5" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="164.2" x="40" y="18.9" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="186.6" x="60" y="7.7" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="199.6" x="80" y="1.2" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="202.0" x="100" y="0.0" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="193.4" x="120" y="4.3" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="174.7" x="140" y="13.7" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="148.0" x="160" y="27.0" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="116.3" x="180" y="42.8" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="83.1" x="200" y="59.5" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="51.9" x="220" y="75.0" rx="5" ry="5" />
                <rect width="10" className="audio-bar" fill="url(#audiogram-background)" height="26.3" x="240" y="87.9" rx="5" ry="5" />
            </svg>
        </div>
    );
};

export default Waveform;

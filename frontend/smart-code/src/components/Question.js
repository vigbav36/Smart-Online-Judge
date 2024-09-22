import '../styles/Question.css';
import React, { useEffect, useState } from 'react';
import { getQuestionDetails } from '../services/Api';

const QuestionDescription = () => {
    const [Question, setQuestion] = useState('');

    useEffect(() => {
        const fetchQuestionDetails = async () => {
            try {
                const question = await getQuestionDetails();
                setQuestion(question);
            } catch (error) {
                console.error('Failed to fetch question details:', error);
            }
        };

        fetchQuestionDetails();
    }, []);

    return (
        <div className='question-description' dangerouslySetInnerHTML={{ __html: Question.description }}>
        </div>
    );
};

export default QuestionDescription;
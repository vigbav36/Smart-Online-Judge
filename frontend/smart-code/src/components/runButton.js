import { Button, Flex } from 'antd';
import { handleSubmision } from '../services/submission';

const RunButton = ({ code , setSubmissionResults, setActiveTab}) => {
    const handleSubmit = async () => {
        await handleSubmision(code, setSubmissionResults, setActiveTab)
    };

    return (
        <Flex gap="small" wrap>
            <Button type="primary" onClick={handleSubmit}>Submit</Button>
        </Flex>
    );
};

export default RunButton;

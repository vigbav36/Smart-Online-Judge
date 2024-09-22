import { submitCode } from "./Api";
import { pingSubmissionbStatus } from "./Api";
import { pingSubmissionResults } from "./Api";

export const handleSubmision = async (code, setSubmissionResults, setActiveTab) => {
    const body = {
        "questionId" : 5,
        "language": "PYTHON",
        "submittedCode": code
    }
    const submissionResponse = await submitCode(body);
    const { submissionId } = submissionResponse;
    pingSubmissionContinuosly(submissionId, setSubmissionResults, setActiveTab)
} 


const pingSubmissionContinuosly = async (submissionId, setSubmissionResults, setActiveTab) => {
    let attempts = 0;
    const maxAttempts = 30;
    const intervalId = setInterval(async () => {
        if (attempts < maxAttempts) {
            try {
                const statusResponse = await pingSubmissionbStatus(submissionId);
                console.log(`Attempt ${attempts + 1}:`, statusResponse);
                
                if (statusResponse.status !== 'PENDING') {
                    console.log(`Submission completed with status: ${statusResponse.status}`);
                    clearInterval(intervalId); 
                    getSubmissionResults(submissionId, setSubmissionResults, setActiveTab);
                }
            } catch (error) {
                console.error('Error fetching submission status:', error);
            }
            attempts += 1;
        } else {
            clearInterval(intervalId); 
        }
    }, 1000);
}

export const getSubmissionResults = async (submissionId, setSubmissionResults, setActiveTab) => {
    const results = await pingSubmissionResults(submissionId)
    setSubmissionResults(results)
    setActiveTab('2')
} 
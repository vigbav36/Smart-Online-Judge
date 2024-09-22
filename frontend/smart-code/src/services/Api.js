export const getQuestionDetails = () => {
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
      
    return fetch("http://localhost:8000/api/questions/5", requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network error');
            }
            return response.json();
        })
        .then((result) => {
            console.log(result);
            return result;
        })
        .catch((error) => {
            console.error('Error fetching question details:', error);
            throw error; 
        });
    };

export const submitCode = async (body) => {
    const response = await fetch('http://localhost:8000/api/submissions', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    });
    
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    
    return response.json();
};



export const pingSubmissionbStatus = (submissionId) => {
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
      
    return fetch(`http://localhost:8000/api/submissions/${submissionId}`, requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network error');
            }
            return response.json();
        })
        .then((result) => {
            console.log(result);
            return result;
        })
        .catch((error) => {
            console.error('Error fetching submission status:', error);
            throw error; 
        });
    };


    export const pingSubmissionResults = (submissionId) => {
        const requestOptions = {
            method: "GET",
            redirect: "follow"
        };
          
        return fetch(`http://localhost:8000/api/submissions/${submissionId}/results`, requestOptions)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Network error');
                }
                return response.json();
            })
            .then((result) => {
                console.log(result);
                return result;
            })
            .catch((error) => {
                console.error('Error fetching submission status:', error);
                throw error; 
            });
        };
    

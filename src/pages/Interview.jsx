import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function Interview() {

    const navigate = useNavigate();

    const [questions, setQuestions] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [answer, setAnswer] = useState("");
    const [feedback, setFeedback] = useState("");

    const interviewId = localStorage.getItem("interviewId");
    const topic = localStorage.getItem("topic") || "Java";

    useEffect(() => {
        fetchQuestions();
    }, []);

    const fetchQuestions = async () => {

        try {

            const response = await api.get(`/questions/topic/${topic}`);

            setQuestions(response.data);

        } catch (error) {

            console.log(error);

            alert("Unable to load questions");
        }
    };

    const submitAnswer = async () => {

        try {

            const question = questions[currentIndex];

            const response = await api.post("/interview/submit", {
                interviewId: Number(interviewId),
                questionId: question.id,
                answer: answer
            });

            if (response.data.feedback) {
                setFeedback(response.data.feedback);
            }

            setAnswer("");

        } catch (error) {

            console.log(error);

            alert("Submission Failed");
        }
    };

    const nextQuestion = () => {

        if (currentIndex + 1 < questions.length) {

            setCurrentIndex(currentIndex + 1);
            setFeedback("");

        } else {

            finishInterview();
        }
    };

    const finishInterview = async () => {

        try {

            const response = await api.post(
                `/interview/finish/${interviewId}`
            );

            localStorage.setItem(
                "result",
                JSON.stringify(response.data)
            );

            navigate("/result");

        } catch (error) {

            console.log(error);

            alert("Unable to finish interview");
        }
    };

    if (questions.length === 0) {

        return <h2 style={{ textAlign: "center" }}>Loading Questions...</h2>;
    }

    return (

        <div style={{ width: "700px", margin: "40px auto" }}>

            <h2>
                Question {currentIndex + 1} / {questions.length}
            </h2>

            <h3>{questions[currentIndex].question}</h3>

            <textarea
                rows="8"
                style={{
                    width: "100%",
                    marginTop: "20px",
                    padding: "10px"
                }}
                value={answer}
                onChange={(e) => setAnswer(e.target.value)}
            />

            <br /><br />

            <button onClick={submitAnswer}>
                Submit Answer
            </button>

            <button
                onClick={nextQuestion}
                style={{ marginLeft: "20px" }}
            >
                Next Question
            </button>

            {feedback && (

                <div
                    style={{
                        marginTop: "30px",
                        padding: "20px",
                        border: "1px solid gray"
                    }}
                >
                    <h3>AI Feedback</h3>

                    <pre>{feedback}</pre>

                </div>

            )}

        </div>

    );
}

export default Interview;
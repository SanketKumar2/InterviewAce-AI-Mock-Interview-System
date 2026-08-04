import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function Result() {

    const navigate = useNavigate();

    const [result, setResult] = useState({});

    useEffect(() => {

        const interviewId = localStorage.getItem("interviewId");

        api.post(`/interview/finish/${interviewId}`)
            .then((response) => {

                setResult(response.data);

            });

    }, []);

    const logout = () => {

        localStorage.clear();

        navigate("/");

    };

    return (

        <div
            style={{
                width: "600px",
                margin: "60px auto",
                textAlign: "center",
                padding: "30px",
                border: "1px solid #ddd",
                borderRadius: "12px",
                boxShadow: "0 0 10px rgba(0,0,0,0.2)"
            }}
        >

            <h1>🎉 Interview Completed</h1>

            <hr />

            <h2>Score : {result.score}</h2>

            <h3>Total Questions : {result.totalQuestions}</h3>

            <p>{result.message}</p>

            <button
                onClick={logout}
                style={{
                    padding: "10px 30px",
                    marginTop: "20px",
                    cursor: "pointer"
                }}
            >
                Logout
            </button>

        </div>

    );

}

export default Result;
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function Dashboard() {

    const navigate = useNavigate();

    const [topic, setTopic] = useState("Java");
    const [totalQuestions, setTotalQuestions] = useState(5);

    const startInterview = async () => {
        

        try {

            const response = await api.post("/interview/start", {
                topic,
                totalQuestions
            });

            localStorage.setItem(
                "interviewId",
                response.data.interviewId
            );
            localStorage.setItem("topic", topic);

            alert(response.data.message);

            navigate("/interview");

        } catch (error) {

            console.log(error);

            if (error.response) {
                alert(JSON.stringify(error.response.data));
            } else {
                alert(error.message);
            }
        }
    };
    const logout = () => {

       localStorage.clear();

       navigate("/");
    };

    return (

        <div style={{ width: "450px", margin: "100px auto" }}>

            <h2>Interview Dashboard</h2>

            <label>Topic</label>

            <select
                value={topic}
                onChange={(e) => setTopic(e.target.value)}
                style={{ width: "100%", padding: "10px", marginBottom: "20px" }}
            >
                <option>Java</option>
                <option>Python</option>
                <option>DBMS</option>
                <option>Operating System</option>
                <option>Computer Networks</option>
            </select>

            <label>Total Questions</label>

            <input
                type="number"
                value={totalQuestions}
                onChange={(e) => setTotalQuestions(Number(e.target.value))}
                style={{ width: "100%", padding: "10px", marginBottom: "20px" }}
            />

            <button
                onClick={startInterview}
                style={{
                    width: "100%",
                    padding: "12px",
                    cursor: "pointer"
                }}
            >
                Start Interview
            </button>
            <button
    onClick={logout}
    style={{
        width: "100%",
        padding: "12px",
        marginTop: "20px",
        cursor: "pointer"
    }}
>
    Logout
</button>

        </div>

    );
}

export default Dashboard;
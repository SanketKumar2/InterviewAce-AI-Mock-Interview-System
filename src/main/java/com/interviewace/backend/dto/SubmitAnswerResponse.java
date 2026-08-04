package com.interviewace.backend.dto;

public class SubmitAnswerResponse {

    private int score;
    private String feedback;

    public SubmitAnswerResponse() {
    }

    public SubmitAnswerResponse(int score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
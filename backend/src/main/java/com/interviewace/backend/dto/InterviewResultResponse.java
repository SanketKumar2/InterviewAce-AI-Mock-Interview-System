package com.interviewace.backend.dto;

public class InterviewResultResponse {

    private int score;
    private int totalQuestions;
    private String message;

    public InterviewResultResponse() {
    }

    public InterviewResultResponse(int score, int totalQuestions, String message) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
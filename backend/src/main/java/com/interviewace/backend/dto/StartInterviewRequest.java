package com.interviewace.backend.dto;

public class StartInterviewRequest {

    private String topic;
    private int totalQuestions;

    public StartInterviewRequest() {
    }

    public StartInterviewRequest(String topic, int totalQuestions) {
        this.topic = topic;
        this.totalQuestions = totalQuestions;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
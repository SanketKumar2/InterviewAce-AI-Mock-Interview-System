package com.interviewace.backend.dto;

public class QuestionRequest {

    private String topic;
    private String difficulty;
    private String question;

    public QuestionRequest() {}

    public QuestionRequest(String topic, String difficulty, String question) {
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
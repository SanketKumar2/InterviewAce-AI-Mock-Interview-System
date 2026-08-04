package com.interviewace.backend.dto;

public class StartInterviewResponse {

    private Long interviewId;
    private String message;

    public StartInterviewResponse() {
    }

    public StartInterviewResponse(Long interviewId, String message) {
        this.interviewId = interviewId;
        this.message = message;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
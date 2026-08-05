package com.interviewace.backend.dto;

public class SubmitAnswerRequest {

    private Long interviewId;
    private Long questionId;
    private String answer;

    public SubmitAnswerRequest() {
    }

    public SubmitAnswerRequest(Long interviewId, Long questionId, String answer) {
        this.interviewId = interviewId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
package com.interviewace.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interview_answers")
public class InterviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long interviewId;

    private Long questionId;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private boolean correct;

    public InterviewAnswer() {
    }

    public InterviewAnswer(Long id,
                           Long interviewId,
                           Long questionId,
                           String answer,
                           boolean correct) {
        this.id = id;
        this.interviewId = interviewId;
        this.questionId = questionId;
        this.answer = answer;
        this.correct = correct;
    }

    public Long getId() {
        return id;
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
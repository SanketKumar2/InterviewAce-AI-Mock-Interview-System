package com.interviewace.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String topic;

    private int totalQuestions;

    private int score;

    private boolean completed;

    public Interview() {
    }

    public Interview(Long id,
                     String userEmail,
                     String topic,
                     int totalQuestions,
                     int score,
                     boolean completed) {

        this.id = id;
        this.userEmail = userEmail;
        this.topic = topic;
        this.totalQuestions = totalQuestions;
        this.score = score;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
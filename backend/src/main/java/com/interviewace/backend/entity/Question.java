package com.interviewace.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    public Question() {
    }

    public Question(Long id, String topic, String difficulty, String question) {
        this.id = id;
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }
}
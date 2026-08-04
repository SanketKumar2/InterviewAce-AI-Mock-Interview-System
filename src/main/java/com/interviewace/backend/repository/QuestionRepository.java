package com.interviewace.backend.repository;

import com.interviewace.backend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTopic(String topic);

    List<Question> findByDifficulty(String difficulty);

    List<Question> findByTopicAndDifficulty(String topic, String difficulty);
}
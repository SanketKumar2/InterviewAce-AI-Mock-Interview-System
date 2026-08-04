package com.interviewace.backend.service;

import com.interviewace.backend.dto.QuestionRequest;
import com.interviewace.backend.dto.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse addQuestion(QuestionRequest request);

    List<QuestionResponse> getAllQuestions();

    List<QuestionResponse> getByTopic(String topic);

    List<QuestionResponse> getByDifficulty(String difficulty);

    List<QuestionResponse> getByTopicAndDifficulty(String topic,
                                                   String difficulty);
}
package com.interviewace.backend.service.impl;

import com.interviewace.backend.dto.QuestionRequest;
import com.interviewace.backend.dto.QuestionResponse;
import com.interviewace.backend.entity.Question;
import com.interviewace.backend.repository.QuestionRepository;
import com.interviewace.backend.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionResponse addQuestion(QuestionRequest request) {

        Question question = new Question();
        question.setTopic(request.getTopic());
        question.setDifficulty(request.getDifficulty());
        question.setQuestion(request.getQuestion());

        Question saved = questionRepository.save(question);

        return new QuestionResponse(
                saved.getId(),
                saved.getTopic(),
                saved.getDifficulty(),
                saved.getQuestion()
        );
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponse> getByTopic(String topic) {
        return questionRepository.findByTopic(topic)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponse> getByDifficulty(String difficulty) {
        return questionRepository.findByDifficulty(difficulty)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponse> getByTopicAndDifficulty(String topic, String difficulty) {
        return questionRepository.findByTopicAndDifficulty(topic, difficulty)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private QuestionResponse convertToResponse(Question question) {
        return new QuestionResponse(
                question.getId(),
                question.getTopic(),
                question.getDifficulty(),
                question.getQuestion()
        );
    }
}
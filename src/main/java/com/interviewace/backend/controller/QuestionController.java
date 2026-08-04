package com.interviewace.backend.controller;

import com.interviewace.backend.dto.QuestionRequest;
import com.interviewace.backend.dto.QuestionResponse;
import com.interviewace.backend.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public QuestionResponse addQuestion(@RequestBody QuestionRequest request) {
        return questionService.addQuestion(request);
    }

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/topic/{topic}")
    public List<QuestionResponse> getByTopic(@PathVariable String topic) {
        return questionService.getByTopic(topic);
    }

    @GetMapping("/difficulty/{difficulty}")
    public List<QuestionResponse> getByDifficulty(@PathVariable String difficulty) {
        return questionService.getByDifficulty(difficulty);
    }

    @GetMapping("/filter")
    public List<QuestionResponse> getByTopicAndDifficulty(
            @RequestParam String topic,
            @RequestParam String difficulty) {

        return questionService.getByTopicAndDifficulty(topic, difficulty);
    }
}
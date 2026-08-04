package com.interviewace.backend.controller;

import com.interviewace.backend.dto.SubmitAnswerResponse;
import com.interviewace.backend.dto.InterviewResultResponse;
import com.interviewace.backend.dto.StartInterviewRequest;
import com.interviewace.backend.dto.StartInterviewResponse;
import com.interviewace.backend.dto.SubmitAnswerRequest;
import com.interviewace.backend.security.JwtService;
import com.interviewace.backend.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;
    private final JwtService jwtService;

    public InterviewController(InterviewService interviewService,
                               JwtService jwtService) {
        this.interviewService = interviewService;
        this.jwtService = jwtService;
    }

    @PostMapping("/start")
    public StartInterviewResponse startInterview(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody StartInterviewRequest request) {

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);

        return interviewService.startInterview(email, request);
    }

    @PostMapping("/submit")
    public SubmitAnswerResponse submitAnswer(
            @RequestBody SubmitAnswerRequest request) {

        return interviewService.submitAnswer(request);
    }

    @PostMapping("/finish/{id}")
    public InterviewResultResponse finishInterview(
            @PathVariable Long id) {

        return interviewService.finishInterview(id);
    }
}
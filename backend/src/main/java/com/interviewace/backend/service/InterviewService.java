package com.interviewace.backend.service;

import com.interviewace.backend.dto.SubmitAnswerResponse;
import com.interviewace.backend.dto.InterviewResultResponse;
import com.interviewace.backend.dto.StartInterviewRequest;
import com.interviewace.backend.dto.StartInterviewResponse;
import com.interviewace.backend.dto.SubmitAnswerRequest;

public interface InterviewService {

    StartInterviewResponse startInterview(
            String userEmail,
            StartInterviewRequest request);

    SubmitAnswerResponse submitAnswer(SubmitAnswerRequest request);

    InterviewResultResponse finishInterview(Long interviewId);
}
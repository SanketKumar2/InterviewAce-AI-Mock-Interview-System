package com.interviewace.backend.service.impl;
import org.springframework.transaction.annotation.Transactional;
import com.interviewace.backend.dto.SubmitAnswerResponse;
import com.interviewace.backend.entity.InterviewAnswer;
import com.interviewace.backend.dto.InterviewResultResponse;
import com.interviewace.backend.dto.StartInterviewRequest;
import com.interviewace.backend.dto.StartInterviewResponse;
import com.interviewace.backend.dto.SubmitAnswerRequest;
import com.interviewace.backend.entity.Interview;
import com.interviewace.backend.repository.InterviewAnswerRepository;
import com.interviewace.backend.repository.InterviewRepository;
import com.interviewace.backend.repository.QuestionRepository;
import com.interviewace.backend.service.InterviewService;
import org.springframework.stereotype.Service;
import com.interviewace.backend.entity.Question;
import com.interviewace.backend.service.GeminiService;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewAnswerRepository interviewAnswerRepository;
    private final QuestionRepository questionRepository;
    private final GeminiService geminiService;

    public InterviewServiceImpl(
            InterviewRepository interviewRepository,
            InterviewAnswerRepository interviewAnswerRepository,
            QuestionRepository questionRepository,
            GeminiService geminiService) {

        this.interviewRepository = interviewRepository;
        this.interviewAnswerRepository = interviewAnswerRepository;
        this.questionRepository = questionRepository;
        this.geminiService = geminiService;
    }

    @Override
    public StartInterviewResponse startInterview(
            String userEmail,
            StartInterviewRequest request) {

        Interview interview = new Interview();

        interview.setUserEmail(userEmail);
        interview.setTopic(request.getTopic());
        interview.setTotalQuestions(request.getTotalQuestions());
        interview.setScore(0);
        interview.setCompleted(false);

        Interview saved = interviewRepository.save(interview);

        return new StartInterviewResponse(
                saved.getId(),
                "Interview Started Successfully"
        );
    }
    @Override
    public SubmitAnswerResponse submitAnswer(SubmitAnswerRequest request) {

        InterviewAnswer answer = new InterviewAnswer();

        answer.setInterviewId(request.getInterviewId());
        answer.setQuestionId(request.getQuestionId());
        answer.setAnswer(request.getAnswer());

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        String feedback = geminiService.evaluateAnswer(
                question.getQuestion(),
                request.getAnswer()
        );

        answer.setCorrect(false);
        interviewAnswerRepository.save(answer);

        // Update Interview Score
        Interview interview = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        int obtainedScore = 8; // Temporary score

        interview.setScore(interview.getScore() + obtainedScore);

        interviewRepository.save(interview);
        interviewRepository.flush();

        Interview updated = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        System.out.println("Updated Score = " + updated.getScore());
        Interview updatedInterview = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        System.out.println("Score in DB after save = " + updatedInterview.getScore());

        return new SubmitAnswerResponse(
                obtainedScore,
                feedback
        );
    }


    @Override
    public InterviewResultResponse finishInterview(Long interviewId) {

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        interview.setCompleted(true);

        interviewRepository.save(interview);

        return new InterviewResultResponse(
                interview.getScore(),
                interview.getTotalQuestions(),
                "Interview Completed Successfully"
        );
    }
}
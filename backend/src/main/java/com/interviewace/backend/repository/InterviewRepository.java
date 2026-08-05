package com.interviewace.backend.repository;

import com.interviewace.backend.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByUserEmail(String userEmail);
}
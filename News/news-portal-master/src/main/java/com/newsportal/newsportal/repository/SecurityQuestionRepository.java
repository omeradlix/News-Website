package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
}

package com.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.Exam.Quiz;

public interface QuizRepositry extends JpaRepository<Quiz, Long> {

}
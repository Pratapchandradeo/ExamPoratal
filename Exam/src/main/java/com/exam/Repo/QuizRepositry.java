package com.exam.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.Models.Exam.Category;
import com.exam.Models.Exam.Quiz;

public interface QuizRepositry extends JpaRepository<Quiz, Long> {

	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);
}

package com.exam.Servises;

import java.util.Set;

import com.exam.Models.Exam.Quiz;

public interface QuizService {

	public Quiz addQuize(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
}
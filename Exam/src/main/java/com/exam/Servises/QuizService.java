package com.exam.Servises;

import java.util.List;
import java.util.Set;

import com.exam.Models.Exam.Category;
import com.exam.Models.Exam.Quiz;

public interface QuizService {

	public Quiz addQuize(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	public List<Quiz> getQUizzesOfCategory(Category category);
	
	public List<Quiz> findByActive();
	
	public List<Quiz> findByCategoryAndActive(Category category);
}

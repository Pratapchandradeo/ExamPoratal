package com.exam.ServisesImp;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Models.Exam.Quiz;
import com.exam.Repo.QuizRepositry;
import com.exam.Servises.QuizService;

@Service
public class QuizServiceImp implements QuizService{

	@Autowired
	private QuizRepositry quizRepositry;
	
	
	
	@Override
	public Quiz addQuize(Quiz quiz) {
		
		return this.quizRepositry.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return this.quizRepositry.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.quizRepositry.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.quizRepositry.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		
		Quiz quiz = new Quiz();
		quiz.setqId(quizId);
		this.quizRepositry.delete(quiz);
		
	}

}

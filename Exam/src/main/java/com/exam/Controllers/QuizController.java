package com.exam.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.exam.Models.Exam.Category;
import com.exam.Models.Exam.Quiz;
import com.exam.ServisesImp.QuizServiceImp;
import com.exam.ServisesImp.categoryServiceImp;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizServiceImp quizServiceImp;
	
	//add quiz
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addHandler(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizServiceImp.addQuize(quiz));
	}
	
	//Update Quiz .................
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuizHandler(@RequestBody Quiz quiz){
		
		return ResponseEntity.ok(this.quizServiceImp.updateQuiz(quiz));
	}
	
	//get Quizzes.....................
	
	@GetMapping("/")
	public ResponseEntity<?> getQuizzesHandler(){
		
		return ResponseEntity.ok(this.quizServiceImp.getQuizes());
	}
	
	//get single Quiz 
	
	@GetMapping("/{qid}")
	public ResponseEntity<Quiz> getQuizzesHandler(@PathVariable("qid") Long qid){
		
		return ResponseEntity.ok(this.quizServiceImp.getQuiz(qid));
	}
	
	
	//delete the Quiz 
	
	@DeleteMapping("/{qid}")
	public  void deleteHandler(@PathVariable("qid") Long qid)
	{
		this.quizServiceImp.deleteQuiz(qid);
	}
	
//	geting specific quiz
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
		Category category= new Category();
		category.setCid(cid);
		return this.quizServiceImp.getQUizzesOfCategory(category);
	}
	
//	get Active Quiz
	@GetMapping("/active")
	public  List<Quiz> getActiveQuizes(){
		return this.quizServiceImp.findByActive();
	}
	
	
	@GetMapping("/category/active/{cid}")
	public  List<Quiz> getActiveQuizesByCategory(@PathVariable("cid") Long id){
		
		Category category = new Category();
		category.setCid(id);
		return this.quizServiceImp.findByCategoryAndActive(category);
	}
	
	

}

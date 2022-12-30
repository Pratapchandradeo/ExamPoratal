package com.exam.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import com.exam.Models.Exam.Questions;
import com.exam.Models.Exam.Quiz;
import com.exam.Servises.QuestionService;
import com.exam.Servises.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	// add questions .............
	
	@PostMapping("/")
	public ResponseEntity<?> addQuestionHandler(@RequestBody Questions questions)
	{
		 return ResponseEntity.ok(this.questionService.addQuestion(questions));
	}

	//update question.............
	
	@PutMapping("/")
	public ResponseEntity<Questions> updateQuestionHandler(@RequestBody Questions questions)
	{
		return ResponseEntity.ok(this.questionService.updateQuestions(questions));
	}
	
	
	//get questions.......
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionHandler(@PathVariable("qid") Long qid){
		
//		Quiz quiz = new  Quiz();
//		quiz.setqId(qid);
//		
//		Set<Questions> questions = this.questionService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questions);
		
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Questions>  questions = quiz.getQuestions();
		
		List<Questions> list = new ArrayList<>(questions);
		
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions()))
		{
			list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		
		list.forEach(q->{
			q.setAnswer("");
		});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);

	}
	
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionHandlerForAdmin(@PathVariable("qid") Long qid){
		
		Quiz quiz = new  Quiz();
		quiz.setqId(qid);
		
		Set<Questions> questions = this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questions);
	}
	
	// get single Question
	
	@GetMapping("/{quId}")
	public Questions getQuestion(@PathVariable("quId") Long quid)
	{
		return this.questionService.getQuestion(quid);
	}
	
	
	// delete question
	
	@DeleteMapping("/{quId}")
	public void deleteQuestion(@PathVariable("quId") Long quId)
	{
		this.questionService.deleteQuestion(quId);
	}
	
	
	//Checking score and matching answer------------
	
	@PostMapping("/answerSubmit")
	public ResponseEntity<?> answerMatching(@RequestBody List<Questions> questions){
		
		Double marksGot=0.0;
		Integer correctAnswer=0;
		Integer attempted=0;
		
		
		
		System.out.println(questions);
		for(Questions q:questions){
//			signle Question
			
			 Questions question = this.questionService.getData(q.getQueId());
			 
			 if(question.getAnswer().equals(q.getGivenAnswer())) {
//				 correct
				 
				 correctAnswer++;
				 Double marks =Double.parseDouble(questions.get(0).getQuiz().getMaxMark())/questions.size();
						 
				 marksGot+=marks;
			 }
			 if(q.getGivenAnswer()!=null) {
				 
				 attempted++;
			 }
			
		}
		Map<Object,Object> mark = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(mark);
	}
	
	
	
	
}

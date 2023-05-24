package com.samta.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samta.main.dto.QuestionDTO;
import com.samta.main.dto.QuestionRequest;
import com.samta.main.dto.QuestionResponse;
import com.samta.main.entities.Question;
import com.samta.main.repositories.QuestionRepository;
import com.samta.main.service.QuestionService;



@RestController
@RequestMapping("/API")
public class MyController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionService questionService;

	
	@RequestMapping(value="/saveQuestions" ,method=RequestMethod.POST)
	public ResponseEntity<List<Question>> saveRandomQuestions() {

		List<Question> questionList = questionService.saveRandomQuestions();

		return ResponseEntity.ok(questionList);

	}
	
	@GetMapping("/play")
	public ResponseEntity<List<QuestionDTO>> getQuestions() {

		List<QuestionDTO> questionList = questionService.getQuestions();

		return ResponseEntity.ok(questionList);

	}
	
	@PostMapping("/next")
	public ResponseEntity<QuestionResponse> checkQuestion(@RequestBody QuestionRequest questionRequest) {

		QuestionResponse questionList = questionService.checkQuestion(questionRequest);

		return ResponseEntity.ok(questionList);

	}

}

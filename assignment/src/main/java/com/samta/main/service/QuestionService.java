package com.samta.main.service;

import java.util.List;

import com.samta.main.dto.QuestionDTO;
import com.samta.main.dto.QuestionRequest;
import com.samta.main.dto.QuestionResponse;
import com.samta.main.entities.Question;



public interface QuestionService {
	
	public List<Question> saveRandomQuestions();
	
	public List<QuestionDTO> getQuestions();
	
	public QuestionResponse checkQuestion(QuestionRequest questionRequest);
	

}

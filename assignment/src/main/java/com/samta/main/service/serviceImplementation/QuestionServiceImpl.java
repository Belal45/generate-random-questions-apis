package com.samta.main.service.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samta.main.dto.QuestionDTO;
import com.samta.main.dto.QuestionRequest;
import com.samta.main.dto.QuestionResponse;
import com.samta.main.entities.Question;
import com.samta.main.repositories.QuestionRepository;
import com.samta.main.service.QuestionService;



@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<Question> saveRandomQuestions() {
		List<Question> questionList = new ArrayList<>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://jservice.io/api/random";

			for (int i = 0; i < 5; i++) {
				ResponseEntity<Question[]> response = restTemplate.getForEntity(url, Question[].class);
				Question[] questionsArray = response.getBody();
				if (questionsArray != null && questionsArray.length > 0) {
					questionList.add(questionsArray[0]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionRepository.saveAll(questionList);
	}

	@Override
	public List<QuestionDTO> getQuestions() {
		List<QuestionDTO> response = new ArrayList<>();

		try {
			List<Object[]> questionObject = questionRepository.findAllQuestions();
			for (Object[] itr : questionObject) {
				QuestionDTO dtoObject = new QuestionDTO();
				dtoObject.setQuestion_id((Long) itr[0]);
				dtoObject.setQuestion((String) itr[1]);
				response.add(dtoObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public QuestionResponse checkQuestion(QuestionRequest questionRequest) {
		QuestionResponse questionResponse = new QuestionResponse();
		try {
			Optional<Question> question = questionRepository.findById(questionRequest.getQuestion_id());
			if (question.isPresent()) {
				questionResponse.setCorrectAnswer(question.get().getAnswer());
				List<Question> questions = questionRepository.findAll();
				int currentIndex = questions.indexOf(question.get());
				QuestionDTO nextQuestion = new QuestionDTO();
				if (currentIndex < questions.size() - 1) {
					Question nextQuestionEntity = questions.get(currentIndex + 1);
					if (nextQuestionEntity != null) {
						nextQuestion.setQuestion_id(nextQuestionEntity.getId());
						nextQuestion.setQuestion(nextQuestionEntity.getQuestion());
					}
				}

				questionResponse.setNextQuestion(nextQuestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return questionResponse;
	}

}

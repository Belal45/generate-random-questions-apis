package com.samta.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samta.main.entities.Question;



public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("select o.id ,o.question from Question o")
	public List<Object[]> findAllQuestions();
	

}

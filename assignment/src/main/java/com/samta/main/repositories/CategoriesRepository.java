package com.samta.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samta.main.entities.Categories;


public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}

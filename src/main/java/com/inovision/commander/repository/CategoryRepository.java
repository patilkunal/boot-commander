package com.inovision.commander.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inovision.commander.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	//Spring Data JPA supports a large number of keywords to build a query
	//ByName will build select query where category.name = ? 
	public Optional<Category> findByName(String name);
	
}

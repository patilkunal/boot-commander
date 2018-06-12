package com.inovision.commander.repository;

import org.springframework.data.repository.CrudRepository;

import com.inovision.commander.model.TestCaseDefinition;

public interface TestCaseDefinitionRepository extends CrudRepository<TestCaseDefinition, Integer>{

	public Iterable<TestCaseDefinition> findByCategoryId(int id);
}

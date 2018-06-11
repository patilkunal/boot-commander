package com.inovision.commander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.model.TestCaseDefinition;
import com.inovision.commander.repository.TestCaseDefinitionRepository;

@Controller
@RequestMapping(value="/testcasedefs", produces="application/json")
public class TestCaseDefinitionController {

	private TestCaseDefinitionRepository testCaseDefinitionRepository;
	
	@Autowired
	public void setTestCaseDefinitionRepository(TestCaseDefinitionRepository testCaseDefinitionRepository) {
		this.testCaseDefinitionRepository = testCaseDefinitionRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<TestCaseDefinition> getAll() {
		return testCaseDefinitionRepository.findAll();
	}
}

package com.inovision.commander.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.model.TestCaseDefinition;
import com.inovision.commander.service.CategoryService;
import com.inovision.commander.service.TestCaseDefinitionService;

@Controller
@RequestMapping(value="/testcasedefs", produces="application/json")
public class TestCaseDefinitionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseDefinitionService.class);
	
	private TestCaseDefinitionService testCaseDefinitionService;
	private CategoryService categoryService;

	@Autowired
	public void setTestCaseDefinitionService(TestCaseDefinitionService testCaseDefinitionService) {
		this.testCaseDefinitionService = testCaseDefinitionService;
	}
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<TestCaseDefinition> getAll() {
		return testCaseDefinitionService.getTestCaseDefinitions();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody TestCaseDefinition getTestCaseDefinition(@PathVariable("id") int id) {
		return testCaseDefinitionService.getTestCaseDefinition(id);
	}

	@RequestMapping(value="/category/{catid}", method=RequestMethod.GET)
	public @ResponseBody Iterable<TestCaseDefinition> getByCategory(@PathVariable("catid") int catid) {
		Category cat = categoryService.getCategory(catid);
		if(cat != null) {
			return testCaseDefinitionService.getTestCaseDefinitionByCategory(cat);
		} else {
			throw new NotfoundException("No such category with id " + catid);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody TestCaseDefinition save(@RequestBody TestCaseDefinition def) {
		LOGGER.info("Trying to save " + def);
		Category category = categoryService.getCategory(def.getId());
		if(category != null) {
			def.setCategory(category);
			return testCaseDefinitionService.saveTestCaseDefinition(def);
		} else {
			throw new NotfoundException("Category for test definition not found");
		}
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody TestCaseDefinition update(@RequestBody TestCaseDefinition def) {
		return testCaseDefinitionService.saveTestCaseDefinition(def);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable("id") int id) throws NotfoundException, OperationNotAllowed {
		testCaseDefinitionService.deleteTestCaseDefinition(id);
		return "success";
	}
	
}

package com.inovision.commander.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.model.TestCaseDefinition;
import com.inovision.commander.repository.TestCaseDefinitionRepository;

@Component
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class TestCaseDefinitionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseDefinitionService.class);

	private TestCaseDefinitionRepository testCaseDefinitionRepository;
	
	@Autowired
	public void setTestCaseDefinitionRepository(TestCaseDefinitionRepository testCaseDefinitionRepository) {
		this.testCaseDefinitionRepository = testCaseDefinitionRepository;
	}

	public Iterable<TestCaseDefinition> getTestCaseDefinitions() {
		return testCaseDefinitionRepository.findAll();
	}
	
	public TestCaseDefinition getTestCaseDefinition(int id) throws NotfoundException {
		try {
			return testCaseDefinitionRepository.findById(id).get();
		} catch(NoSuchElementException e) {			
			throw new NotfoundException("Test case definition not found with id: " + id);
		}
	}

	public Iterable<TestCaseDefinition> getTestCaseDefinitionByCategory(Category cat) throws NotfoundException {		
		return testCaseDefinitionRepository.findByCategoryId(cat.getId());
	}
	
	@Transactional(readOnly=false)
	public TestCaseDefinition saveTestCaseDefinition(TestCaseDefinition def) throws NotfoundException {
		return testCaseDefinitionRepository.save(def);
	}
	
	@Transactional(readOnly=false)
	public void deleteTestCaseDefinition(int id) throws NotfoundException, OperationNotAllowed {
		LOGGER.debug("Deleting Test case definition by id " + id);
		if(testCaseDefinitionRepository.existsById(id)) {
			TestCaseDefinition tcd = testCaseDefinitionRepository.findById(id).get();
			if(tcd.getTestInstances() != null && tcd.getTestInstances().size() > 0) {
				LOGGER.warn("Cannot delete defintion without deleting the instances");
				throw new OperationNotAllowed("Cannot delete defintion without deleting the instances");
			}
		} else {
			throw new NotfoundException("Test case definition not found with id: " + id);
		}
	}
		
}

package com.inovision.commander.service;

import java.util.Optional;

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
	
	private static final String DELETING_TEST_CASE_DEFINITION_BY_ID = "Deleting Test case definition by id %d";
	private static final String CANNOT_DELETE_DEFINTION_WITHOUT_DELETING_THE_INSTANCES = "Cannot delete defintion without deleting the instances";
	private static final String TEST_CASE_DEFINITION_NOT_FOUND = "Test case definition not found with id: %d";

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
		Optional<TestCaseDefinition> optional = testCaseDefinitionRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {			
			throw new NotfoundException(String.format(TEST_CASE_DEFINITION_NOT_FOUND, id));
		}
	}

	public Iterable<TestCaseDefinition> getTestCaseDefinitionByCategory(Category cat) {		
		return testCaseDefinitionRepository.findByCategoryId(cat.getId());
	}
	
	@Transactional(readOnly=false)
	public TestCaseDefinition saveTestCaseDefinition(TestCaseDefinition def) {
		return testCaseDefinitionRepository.save(def);
	}

	@Transactional(readOnly=false)
	public TestCaseDefinition updateTestCaseDefinition(TestCaseDefinition def) throws NotfoundException {
		Optional<TestCaseDefinition> optional = testCaseDefinitionRepository.findById(def.getId());
		if (optional.isPresent()) {
			return testCaseDefinitionRepository.save(def);
		} else {
			throw new NotfoundException(String.format(TEST_CASE_DEFINITION_NOT_FOUND, def.getId()));
		}
	}
	
	@Transactional(readOnly=false)
	public void deleteTestCaseDefinition(int id) throws NotfoundException, OperationNotAllowed {
		LOGGER.debug(String.format(DELETING_TEST_CASE_DEFINITION_BY_ID, id));
		Optional<TestCaseDefinition> optional = testCaseDefinitionRepository.findById(id);
		if(optional.isPresent()) {
			TestCaseDefinition tcd = optional.get();
			if(tcd.getTestInstances() != null && !tcd.getTestInstances().isEmpty()) {
				LOGGER.warn(CANNOT_DELETE_DEFINTION_WITHOUT_DELETING_THE_INSTANCES);
				throw new OperationNotAllowed(CANNOT_DELETE_DEFINTION_WITHOUT_DELETING_THE_INSTANCES);
			}
		} else {
			throw new NotfoundException(String.format(TEST_CASE_DEFINITION_NOT_FOUND, id));
		}
	}
		
}

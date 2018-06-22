package com.inovision.commander.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.Category;
import com.inovision.commander.model.ContentType;
import com.inovision.commander.model.HttpMethod;
import com.inovision.commander.model.TestCaseDefinition;
import com.inovision.commander.model.TestCaseInstance;
import com.inovision.commander.model.ValidateType;
import com.inovision.commander.repository.TestCaseDefinitionRepository;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class TestCaseDefinitionServiceTest extends TestCase {

	@TestConfiguration
	static class TestCaseDefinitionServiceContextConfiguration {
		
		@Bean
		public TestCaseDefinitionService testCaseDefinitionService() {
			return new TestCaseDefinitionService();
		}
	}
	
	@Autowired
	private TestCaseDefinitionService testCaseDefinitionService;
	
	@MockBean
	private TestCaseDefinitionRepository testCaseDefinitionRepository;
	
	@Before
	public void setUp() {
		Category cat1 = new Category(1, "cat1", "desc1", null, null);
		TestCaseDefinition td1 = new TestCaseDefinition(1, "def1", "desc1", cat1, "http://localhost", HttpMethod.GET,
				null, ContentType.APPLICATION_JSON, false, null, false, ValidateType.JSON,
				null);
		TestCaseDefinition td2 = new TestCaseDefinition(2, "def2", "desc2", cat1, "http://localhost", HttpMethod.GET,
				null, ContentType.APPLICATION_JSON, false, null, false, ValidateType.JSON,
				null);
		
		Mockito.when(testCaseDefinitionRepository.findAll())
			.thenReturn(Arrays.asList(td1, td2));
		Mockito.when(testCaseDefinitionRepository.findById(1))
			.thenReturn(Optional.of(td1));
		Mockito.when(testCaseDefinitionRepository.findById(100))
		.thenReturn(Optional.empty());

	}
	
	@Test
	public void testGetAllDefinition() {
		Iterable<TestCaseDefinition> itr = testCaseDefinitionService.getTestCaseDefinitions();
		assertNotNull(itr);
		List<TestCaseDefinition> list = new ArrayList<>(3);
		itr.forEach(td -> list.add(td));
		assertFalse(list.isEmpty());
		assertEquals(2, list.size());
	}
	
	@Test
	public void testGetDefinition() throws Exception {
		TestCaseDefinition td = testCaseDefinitionService.getTestCaseDefinition(1);
		assertNotNull(td);
		assertTrue("def1".equals(td.getName()));
		try {
			testCaseDefinitionService.getTestCaseDefinition(100);
			fail("Should throw NotfoundException");
		} catch(NotfoundException e) {
			//expected
		}
	}
}

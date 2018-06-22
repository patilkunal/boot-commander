package com.inovision.commander.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCaseDefinitionTest extends TestCase {

	@Test
	public void testConstructor() {
		TestCaseDefinition td = new TestCaseDefinition();
		assertEquals(0, td.getId());
		
		TestCaseDefinition td1 = new TestCaseDefinition(1, "def1", "desc1", null, "http://localhost", HttpMethod.GET,
				"http data", ContentType.APPLICATION_JSON, false, null, false, ValidateType.JSON,
				null);
		assertEquals(1, td1.getId());
		assertTrue("def1".equals(td1.getName()));
		assertTrue("desc1".equals(td1.getDescription()));
		assertTrue("http://localhost".equals(td1.getRestUrl()));
		assertEquals(HttpMethod.GET, td1.getHttpMethod());
		assertTrue("http data".equals(td1.getHttpData()));
		
	}
}

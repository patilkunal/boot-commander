package com.inovision.commander.model;

import junit.framework.TestCase;

public class CategoryTest extends TestCase {

	public void testConstructor() {
		Category cat = new Category(1, "cat1", "desc1", null, null);
		assertEquals(1, cat.getId());
		assertTrue("cat1".equals(cat.getName()));
		assertTrue("desc1".equals(cat.getDescription()));
		assertTrue(cat.getHost() == null);
		assertTrue(cat.getTestCases() == null);
	}
}

package com.inovision.commander.model;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class CategoryTest extends TestCase {

	public void testConstructor() {
		Category cat = new Category(1, "cat1", "desc1", null, null);
		assertEquals(1, cat.getId());
		assertTrue("cat1".equals(cat.getName()));
		assertTrue("desc1".equals(cat.getDescription()));
		assertTrue(cat.getHost() == null);
		assertTrue(cat.getTestCases() == null);
	}
	
	public void testEquals() {
		Category first = new Category(1, "cat1", "desc1", null, null);
		Category second = new Category(1, "cat2", "desc2", Arrays.asList(new Host()), null);
		Category more = new Category(1, "cat2", "desc2", Arrays.asList(new Host()), Arrays.asList(new TestCaseDefinition()));
		Category different = new Category(2, "cat2", "desc3", null, null);
		EqualsVerifier
			.forRelaxedEqualExamples(first, second, more)
			.andUnequalExample(different)
			.withPrefabValues(List.class, Arrays.asList(new Host()), Arrays.asList(new Host(), new Host())).verify();
		EqualsVerifier
			.forClass(Category.class)
			.suppress(Warning.SURROGATE_KEY)
			.withPrefabValues(List.class, Arrays.asList(new Host()), Arrays.asList(new Host(), new Host()))
			.verify();
	}
}

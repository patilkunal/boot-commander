package com.inovision.commander.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.model.Category;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest extends TestCase {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Category> itr = categoryRepository.findAll();
		List<Category> list = new ArrayList<Category>(3);
		assertNotNull(itr); 
		assertTrue(itr.iterator().hasNext());
		
		itr.forEach(cat -> { list.add(cat); });
		
		assertEquals(3, list.size());		
	}
	
	public void testFindOne() {
		Category cat = categoryRepository.findById(1).get();
		assertNotNull(cat);
		assertTrue(cat.getName().equals("Category 1"));
	}
	
	
}

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
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.repository.CategoryRepository;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class CategoryServiceTest extends TestCase { 

	// test spring configuration which provides the service bean class in context  
	@TestConfiguration
	static class CategoryServiceImplTestContextConfiguration {
		
		@Bean
		public CategoryService categoryService() {
			return new CategoryService();
		}
		
	}
	
	@Autowired
	private CategoryService categoryService;
	
	@MockBean
	private CategoryRepository categoryRespository;
	
	@Before
	public void setUp() {
		Category cat1 = new Category(1, "Category 1", "Desc 1", null, null);
		Category cat2 = new Category(2, "Category 2", "Desc 2", null, null);
		Category cat3 = new Category(3, "Category 3", "Desc 3", null, null);
	    Iterable<Category> itr = Arrays.asList(cat1, cat2, cat3);
	 
	    Mockito.when(categoryRespository.findAll())
	    	.thenReturn(itr);

	    Mockito.when(categoryRespository.findById(1))
    		.thenReturn(Optional.of(cat1));
	    
	    Mockito.when(categoryRespository.findById(100))
			.thenReturn(Optional.empty());
	}
	
	@Test
	public void testGetAllCategories() {
		Iterable<Category> itr = categoryService.getAllCategories();
		assertNotNull(itr);
		List<Category> list = new ArrayList<>(3);
		itr.forEach(cat -> list.add(cat));
		assertEquals(3, list.size());
	}

	@Test
	public void testGetCategoryByid() throws NotfoundException {
		Category cat = categoryService.getCategory(1);
		assertNotNull(cat);
	}
	
	@Test
	public void testGetCategoryNotFound() {
		try {
			categoryService.getCategory(100);
			fail("Should throw NotfoundException");
		} catch(NotfoundException nfe) {
			assertTrue(nfe.getMessage().contains("not found"));
		}
		
	}

	@Test
	public void testDeleteCategory() throws NotfoundException, OperationNotAllowed {
		categoryService.deleteCategory(1);		
	}
	
}

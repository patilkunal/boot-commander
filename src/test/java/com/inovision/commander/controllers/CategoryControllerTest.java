package com.inovision.commander.controllers;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.model.Category;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends TestCase {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCategories() {
		ResponseEntity<Category[]> resp = this.restTemplate.getForEntity("/categories", Category[].class);
		Category[] cats = resp.getBody();
		assertNotNull(cats);
		assertEquals(3, cats.length);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" +cats[0]);
		Category cat1 = cats[0];
		assertNotNull(cat1);
		assertTrue(cat1.getName().equals("Category 1"));
	}
	
	public void testGetCategory() {
		Category cat = this.restTemplate.getForObject("/categories/3", Category.class);
		assertNotNull(cat);
		assertTrue(cat.getName().equals("Category 3"));
	}
	
	public void testSaveAndDeleteCategory() {
		Category cat = new Category();
		cat.setName("Cat 4");
		cat.setDescription("Desc 4");
		ResponseEntity<Category> resp = this.restTemplate.postForEntity("/categories", cat, Category.class);
		Category saved = resp.getBody();
		assertNotNull(saved);
		assertTrue(cat.getName().equals(saved.getName()));

		ResponseEntity<Category[]> resp2 = this.restTemplate.getForEntity("/categories", Category[].class);
		Category[] cats = resp2.getBody();
		assertNotNull(cats);
		assertEquals(4, cats.length);
		
		
	}
	
}

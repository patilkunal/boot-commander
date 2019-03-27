package com.inovision.commander.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.BaseControllerWithAuthTest;
import com.inovision.commander.model.Category;
import com.inovision.commander.model.ErrorResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends BaseControllerWithAuthTest {

//	@Autowired
//	private TestRestTemplate restTemplate;
	
	@Test
	public void test1GetCategories() {
		ResponseEntity<Category[]> resp = this.restTemplate.getForEntity("/categories", Category[].class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		Category[] cats = resp.getBody();
		assertNotNull(cats);
		assertEquals(3, cats.length);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" +cats[0]);
		Category cat1 = cats[0];
		assertNotNull(cat1);
		assertTrue(cat1.getName().equals("Category 1"));
	}
	
	@Test
	public void test2GetCategory() {
		Category cat = this.restTemplate.getForObject("/categories/3", Category.class);
		assertNotNull(cat);
		assertNotNull(cat.getName());
		assertTrue(cat.getName().equals("Category 3"));
	}
	
	@Test
	public void test3SaveAndDeleteCategory() {
		Category cat = new Category();
		cat.setName("Cat 4");
		cat.setDescription("Desc 4");
		ResponseEntity<Category> resp = this.restTemplate.postForEntity("/categories", cat, Category.class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		Category saved = resp.getBody();
		assertNotNull(saved);
		assertTrue(cat.getName().equals(saved.getName()));
		
		saved.setName("Updated Cat 4");
		resp = this.restTemplate.exchange("/categories/" + saved.getId(), HttpMethod.PUT, new HttpEntity<Category>(saved), Category.class);
		assertNotNull(resp);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		Category updated = resp.getBody();
		assertNotNull(updated);
		assertTrue(updated.getName().equals("Updated Cat 4"));

		ResponseEntity<Category[]> resp2 = this.restTemplate.getForEntity("/categories", Category[].class);
		assertTrue(resp2.getStatusCode().is2xxSuccessful());
		Category[] cats = resp2.getBody();
		assertNotNull(cats);
		assertEquals(4, cats.length);
		
		
	}
	
	@Test
	public void testCategoryNotFound() {
		ErrorResponse error = this.restTemplate.getForObject("/categories/300", ErrorResponse.class);
		assertNotNull(error);
		System.out.println(">>>>>" + error);
	}

	@Test
	public void testCategoryNotFound2() {
		ResponseEntity<ErrorResponse> resp = this.restTemplate.exchange("/categories/500", HttpMethod.GET, null, ErrorResponse.class);
		
		assertNotNull(resp);
		assert(resp.getStatusCodeValue() == 404);
		ErrorResponse error = resp.getBody();
		assertNotNull(error);
		assertEquals(404, error.getStatus());
		assertTrue(error.getMessage().contains("Category not found"));
		//System.out.println(">>>>>>>> " + (resp.toString()));
		
	}
	
}

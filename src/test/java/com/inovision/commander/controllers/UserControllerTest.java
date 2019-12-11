package com.inovision.commander.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.BaseControllerWithAuthTest;
import com.inovision.commander.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends BaseControllerWithAuthTest {

	@Test
	public void testGetUsersNative() {
		ResponseEntity<User[]> resp = this.restTemplate.getForEntity("/user", User[].class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		User[] users = resp.getBody();
		assertNotNull(users);
		assertTrue(users.length == 1);
		System.out.println(users[0]);
	}
}

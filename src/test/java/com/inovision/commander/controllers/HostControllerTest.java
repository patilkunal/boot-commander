package com.inovision.commander.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.inovision.commander.model.Host;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HostControllerTest extends TestCase {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetHosts() {
		ResponseEntity<Host[]> resp = this.restTemplate.getForEntity("/hosts", Host[].class);
		Host[] hosts = resp.getBody();
		assertNotNull(hosts);
		assertEquals(3, hosts.length);
		Host host1 = hosts[0];
		assertNotNull(host1);
		assertTrue("Host 1".equals(host1.getName()));
		assertTrue("hostname1".equals(host1.getHostName()));

		Host host2 = hosts[1];
		assertNotNull(host2);
		assertTrue("Host 2".equals(host2.getName()));
		assertTrue(host2.isSecureHttp());
	}
	
}

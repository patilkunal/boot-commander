package com.inovision.commander.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.inovision.commander.BaseControllerWithAuthTest;
import com.inovision.commander.model.ErrorResponse;
import com.inovision.commander.model.Host;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HostControllerTest extends BaseControllerWithAuthTest {

	@Autowired
	private TestRestTemplate restTemplate;

	//@Test
	public void xtestGetHosts() {
		ResponseEntity<Host[]> resp = this.restTemplate.getForEntity("/hosts", Host[].class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
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
	
	@Test
	public void testHostNotFound() {
		try {
			HttpEntity<Host> ent = new HttpEntity<Host>((Host)null);
			ResponseEntity<ErrorResponse> resp = this.restTemplate.exchange("/hosts/5", HttpMethod.GET, ent, ErrorResponse.class);
			assert(resp.getStatusCodeValue() == 404);
			System.out.println(">>>>>>>> " + (resp.toString()));
			//assert(resp.getStatusCode().getReasonPhrase().contains("Host not found"));
		} catch(HttpClientErrorException rce) {
			System.out.println("******" + rce);
			String msg = rce.getMessage();
			assertNotNull(msg);
			System.out.println(">>>>> " + msg);
		} catch(HttpServerErrorException rse) {
			System.out.println("******" + rse);
			String msg = rse.getMessage();
			assertNotNull(msg);
			System.out.println(">>>>> " + msg);
		}				
	}
	
}

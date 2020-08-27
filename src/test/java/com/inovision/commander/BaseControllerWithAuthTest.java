package com.inovision.commander;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations="classpath:test.properties")
public abstract class BaseControllerWithAuthTest {
	
	private static String jwtToken = null;
	
	@Autowired
	protected TestRestTemplate restTemplate;

	private boolean initialized = false;
	
	@Before
	public void initializeTestTemplate() {
		if(!initialized) {
			String token = getJWTToken();
			List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
			interceptors.add(new CustomRequestInterceptor(token));
			restTemplate.getRestTemplate().setInterceptors(interceptors);
			initialized = true;
		}
	}
	
	private String getJWTToken() {
		if(jwtToken != null) return jwtToken;
		Map<String, String> map = new HashMap<String, String>();
		//set data-h2.sql for these values
		map.put("username", "kunal");
		map.put("password", "pass123");
		
		ResponseEntity<String> resp= restTemplate.postForEntity("/login", map, String.class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		HttpHeaders headers = resp.getHeaders();
		jwtToken = (String) headers.getFirst(HttpHeaders.AUTHORIZATION);
		return jwtToken;
	}
	
	private static class CustomRequestInterceptor implements ClientHttpRequestInterceptor {
		private String jwtToken;
		
		public CustomRequestInterceptor(String token) {
			this.jwtToken = token;
		}
		
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, jwtToken );
            //request.getHeaders().add(HttpHeaders.ACCEPT, "application/json" );
            return execution.execute(request, body);
        }
    }	
}

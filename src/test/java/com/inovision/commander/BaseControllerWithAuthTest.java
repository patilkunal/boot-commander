package com.inovision.commander;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovision.commander.model.ErrorResponse;

public abstract class BaseControllerWithAuthTest {
	
	private static String jwtToken = null;
	
	@Autowired
	protected TestRestTemplate restTemplate;

	/*
	@BeforeClass
	public static void populateUsers() {
		Map<String, String> map = new HashMap<String, String>();
		//set data.sql for these values
		map.put("name", "John");
		map.put("email", "john@inovision.com");
		map.put("userName", "johnuser");
		map.put("password", "password123");
		RestTemplate templ = new RestTemplate();
		ResponseEntity<String> resp= templ.postForEntity("/user/signup", map, String.class);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	*/
	
	@Before
	public void initializeTestTemplate() {
		String token = getJWTToken();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new CustomRequestInterceptor(token));
		restTemplate.getRestTemplate().setInterceptors(interceptors);
		/*
		restTemplate.getRestTemplate().getMessageConverters().add(new HttpMessageConverter<ErrorResponse>() {
			
			ObjectMapper MAPPER = new ObjectMapper();
			
			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType) {				
				return clazz.isAssignableFrom(ErrorResponse.class) && mediaType.equals(MediaType.APPLICATION_JSON);
			}
			
			@Override
			public ErrorResponse read(Class<? extends ErrorResponse> clazz, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				return MAPPER.readValue(inputMessage.getBody(), ErrorResponse.class);
			}
			
			@Override
			public List<MediaType> getSupportedMediaTypes() {				
				return Arrays.asList(MediaType.APPLICATION_JSON);
			}

			@Override
			public boolean canWrite(Class<?> clazz, MediaType mediaType) {
				return canRead(clazz, mediaType);
			}

			@Override
			public void write(ErrorResponse t, MediaType contentType, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				if(contentType.equals(MediaType.APPLICATION_JSON)) {
					MAPPER.writeValue(outputMessage.getBody(), t);
				} else {
					throw new HttpMessageNotWritableException("Support only JSON");
				}
			}
			
		});
		*/
	}
	
	private String getJWTToken() {
		if(jwtToken != null) return jwtToken;
		Map<String, String> map = new HashMap<String, String>();
		//set data.sql for these values
		map.put("userName", "kunal");
		map.put("password", "pass123");
		
		ResponseEntity<String> resp= this.restTemplate.postForEntity("/login", map, String.class);
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

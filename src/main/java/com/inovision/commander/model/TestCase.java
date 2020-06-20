package com.inovision.commander.model;

import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class TestCase {

	private int id;
	private int testCategoryId;
	private String name;
	private String description;
	private String restUrl;
	private HttpMethod method = HttpMethod.GET;
	private String data;

	private int instances;

	@Override
	public String toString() {
		return String.format("TestCase [name: %1$s] [RestURL: %2$s] [Data: %3$s] [Method: %4$s]]", name, restUrl, data, method.name());
	}
}
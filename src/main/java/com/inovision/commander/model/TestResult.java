package com.inovision.commander.model;

import lombok.Data;

@Data
public class TestResult {

	private int testInstanceId;
	private int hostId;
	private String name;
	private int returnCode;
	private String error;
	private String result;
	private boolean success;

}

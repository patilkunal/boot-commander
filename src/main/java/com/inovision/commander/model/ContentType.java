package com.inovision.commander.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContentType {

	APPLICATION_JSON("application/json"),
	APPLICATION_XML("application/xml"),
	TEXT_PLAIN("text/plain"),
	TEXT_HTML("text/html");
	
	private final String type;
	
	private ContentType(String type) {
		this.type=type;
	}
	
	@JsonValue
	public String getType() {
		return type;
	}

	@JsonCreator
	public static ContentType fromString(String type) {
		for(ContentType t : values()) {
			if(t.getType().equalsIgnoreCase(type))
				return t;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getType();
	}
	
	
}

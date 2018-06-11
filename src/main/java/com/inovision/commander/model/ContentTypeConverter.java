package com.inovision.commander.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ContentTypeConverter implements AttributeConverter<ContentType, String>{

	@Override
	public String convertToDatabaseColumn(ContentType attribute) {
		return attribute.getType();
	}
	
	@Override
	public ContentType convertToEntityAttribute(String dbData) {
		return ContentType.fromString(dbData);
	}
}

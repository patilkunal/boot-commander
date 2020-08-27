package com.inovision.commander.exception.efx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("attribute_name")
    private String attributeName;
    @JsonProperty("message")
    private String message;
    @JsonProperty("value")
    private String value;

    public ErrorMessage(FieldError fieldError) {
        this.errorCode = "400";
        this.attributeName = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
        Object objectValue = fieldError.getRejectedValue();
        this.value = (objectValue == null) ? "null" : String.valueOf(objectValue);
    }
}
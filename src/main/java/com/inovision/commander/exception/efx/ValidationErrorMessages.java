package com.inovision.commander.exception.efx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ValidationErrorMessages {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("create_date_utc")
    private Date createdDateUTC;

    @JsonProperty("case_record_version")
    private String caseRecordVersion;

    private HttpStatus status;
    private String message;
    private String source;

    @JsonProperty("errors")
    private List<ErrorMessage> errorMessageList;


    public ValidationErrorMessages(HttpStatus status, String message,String source, List<ErrorMessage> errorMessageList) {
        this.status = status;
        this.message = message;
        this.source = source;
        this.errorMessageList = errorMessageList;
        this.transactionId = "123"; //RequestCorrelationUtils.getCurrentTransactionId();
        this.sessionId = "123" ; //RequestCorrelationUtils.getCurrentSessionId();
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}

package com.inovision.commander.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TESTCASE_RUN")
public class TestCaseRun {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="TESTCASE_INSTANCE_ID")
	private TestCaseInstance testCaseInstance;
	
	@OneToOne
	@JoinColumn(name="HOST_ID")
	private Host host;
	
	private boolean success;	
	private Date runDate;	
	private int returnCode;
	private String error;
	private String result;
	private String contentType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TestCaseInstance getTestCaseInstance() {
		return testCaseInstance;
	}
	public void setTestCaseInstance(TestCaseInstance testCaseInstance) {
		this.testCaseInstance = testCaseInstance;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Override
	public String toString() {
		return "TestCaseRun [id=" + id + ", testCaseInstance=" + testCaseInstance + ", host=" + host + ", success="
				+ success + ", runDate=" + runDate + ", returnCode=" + returnCode + ", error=" + error + ", result="
				+ result + ", contentType=" + contentType + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof TestCaseRun) && (this.id == ((TestCaseRun)obj).id);
	}
	
	@Override
	public int hashCode() {
		return 37 * id;
	}
}

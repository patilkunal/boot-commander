package com.inovision.commander.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TESTCASE_INSTANCE")
public class TestCaseInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="TESTCASE_ID")
	private TestCaseDefinition testCaseDefinition;
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(name="VALIDATE_OUTPUT")
	private boolean validateOutput;
	
	@Column(name="OUTPUT_TEMPLATE")
	private String outputTemplate;
	
	@Column(name="ALLOW_BLANK_OUTPUT")
	private boolean allowBlankOutput;
	
	@Column(name="VALIDATE_TYPE")
	private ValidateType validateType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestCaseDefinition getTestCaseDefinition() {
		return testCaseDefinition;
	}

	public void setTestCaseDefinition(TestCaseDefinition testCaseDefinition) {
		this.testCaseDefinition = testCaseDefinition;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isValidateOutput() {
		return validateOutput;
	}

	public void setValidateOutput(boolean validateOutput) {
		this.validateOutput = validateOutput;
	}

	public String getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(String outputTemplate) {
		this.outputTemplate = outputTemplate;
	}

	public boolean isAllowBlankOutput() {
		return allowBlankOutput;
	}

	public void setAllowBlankOutput(boolean allowBlankOutput) {
		this.allowBlankOutput = allowBlankOutput;
	}

	public ValidateType getValidateType() {
		return validateType;
	}

	public void setValidateType(ValidateType validateType) {
		this.validateType = validateType;
	}
	
	
}

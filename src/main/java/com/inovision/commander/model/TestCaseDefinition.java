package com.inovision.commander.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "testcase")
public class TestCaseDefinition {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "testcase_id_seq")
	@SequenceGenerator(name = "testcase_id_seq", sequenceName = "testcase_id_seq", allocationSize = 1)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "test_category_id")
	private Category category;

	@Column(name = "rest_url")
	private String restUrl;

	@Column(name = "http_method")
	@Enumerated(EnumType.STRING)
	private HttpMethod httpMethod;

	@Column(name = "http_data")
	private String httpData;

	@Column(name = "content_type")
	@Convert(converter = ContentTypeConverter.class)
	@Enumerated(EnumType.STRING)
	private ContentType contentType;

	@Column(name = "validate_output")
	private boolean validateOutput;

	@Column(name = "output_template")
	private String outputTemplate;

	@Column(name = "allow_blank_output")
	private boolean allowBlankOutput;

	@Column(name = "validate_type")
	@Enumerated(EnumType.STRING)
	private ValidateType validateType;

	@OneToMany(mappedBy = "testCaseDefinition")
	@JsonIgnore
	private List<TestCaseInstance> testInstances;

	public TestCaseDefinition() {
	}

	public TestCaseDefinition(int id, String name, String description, Category cat, String restUrl, HttpMethod method,
			String httpData, ContentType ctype, boolean validate, String outTemplate, boolean allowBlank, ValidateType validateType,
			List<TestCaseInstance> testInstances) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = cat;
		this.restUrl = restUrl;
		this.httpMethod = method;
		this.httpData = httpData;
		this.contentType = ctype;
		this.validateOutput = validate;
		this.outputTemplate = outTemplate;
		this.allowBlankOutput = allowBlank;
		this.validateType = validateType;
		this.testInstances = testInstances;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpData() {
		return httpData;
	}

	public void setHttpData(String httpData) {
		this.httpData = httpData;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
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

	public List<TestCaseInstance> getTestInstances() {
		return testInstances;
	}

	public void setTestInstances(List<TestCaseInstance> testInstances) {
		this.testInstances = testInstances;
	}

	@Override
	public String toString() {
		return "TestCaseDefinition [id=" + id + ", name=" + name + ", description=" + description + ", category="
				+ category + ", restUrl=" + restUrl + ", httpMethod=" + httpMethod + ", httpData=" + httpData
				+ ", contentType=" + contentType + ", validateOutput=" + validateOutput + ", outputTemplate="
				+ outputTemplate + ", allowBlankOutput=" + allowBlankOutput + ", validateType=" + validateType + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof TestCaseDefinition) && (((TestCaseDefinition)obj).id == id);
	}
	
	@Override
	public int hashCode() {
		return 31 * id;
	}

}

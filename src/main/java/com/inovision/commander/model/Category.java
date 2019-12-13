package com.inovision.commander.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Category Model")
@Entity
@Table(name="TEST_CATEGORY")
public class Category {

	@ApiModelProperty(value = "The unique id value of category")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ApiModelProperty(value = "Category name")
	@Column(name="NAME")
	private String name;
	
	@ApiModelProperty(value = "Category description")
	@Column(name="DESCRIPTION")
	private String description;
	
	@Transient
	private int testCount = 0;

	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List<Host> host;

	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List<TestCaseDefinition> testCases;
	
	
	public Category() {		
	}
	
	public Category(int id, String name, String desc, List<Host> host, List<TestCaseDefinition> testdefs) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.host = host;
		this.testCases = testdefs;
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

	public List<Host> getHost() {
		return host;
	}
	
	public void setHost(List<Host> host) {
		this.host = host;
	}
	
	public List<TestCaseDefinition> getTestCases() {
		return testCases;
	}
	
	public void setTestCases(List<TestCaseDefinition> testCases) {
		this.testCases = testCases;
	}
	
	public int getTestCount() {
		return this.testCases != null ? this.testCases.size() :  0;
	}
	
	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Category) && (this.id == ((Category)obj).id );
	}
	
	@Override
	public int hashCode() {
		return 37 * id;
	}
	
}

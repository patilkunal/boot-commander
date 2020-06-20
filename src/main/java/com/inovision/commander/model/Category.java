package com.inovision.commander.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(description = "Category Model")
@Entity
@Table(name="test_category")
@Data
@EqualsAndHashCode(of = {"id"})
public class Category {

	@ApiModelProperty(value = "The unique id value of category")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "category_id_seq")
	@SequenceGenerator(name = "category_id_seq", sequenceName = "test_category_id_seq", allocationSize = 1)
	private int id;
	
	@ApiModelProperty(value = "Category name")
	@Column(name="name")
	private String name;
	
	@ApiModelProperty(value = "Category description")
	@Column(name="description")
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}

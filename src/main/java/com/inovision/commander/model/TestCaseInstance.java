package com.inovision.commander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name="testcase_instance")
@Data
@EqualsAndHashCode(of = {"id"})
public class TestCaseInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="testcase_id")
	private TestCaseDefinition testCaseDefinition;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="validate_output")
	private boolean validateOutput;
	
	@Column(name="output_template")
	private String outputTemplate;
	
	@Column(name="allow_blank_output")
	private boolean allowBlankOutput;
	
	@Column(name="validate_type")
	@Enumerated(EnumType.STRING)
	private ValidateType validateType;

	@Override
	public String toString() {
		return "TestCaseInstance [id=" + id + ", testCaseDefinition=" + testCaseDefinition + ", user=" + user
				+ ", validateOutput=" + validateOutput + ", outputTemplate=" + outputTemplate + ", allowBlankOutput="
				+ allowBlankOutput + ", validateType=" + validateType + "]";
	}
	
}

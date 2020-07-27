package com.inovision.commander.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="testcase_run")
@Data
@EqualsAndHashCode(of = "id")
public class TestCaseRun {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="testcase_instance_id")
	private TestCaseInstance testCaseInstance;
	
	@OneToOne
	@JoinColumn(name="host_id")
	private Host host;
	
	private boolean success;	
	private Date runDate;	
	private int returnCode;
	private String error;
	private String result;
	private String contentType;
	
	@Override
	public String toString() {
		return "TestCaseRun [id=" + id + ", testCaseInstance=" + testCaseInstance + ", host=" + host + ", success="
				+ success + ", runDate=" + runDate + ", returnCode=" + returnCode + ", error=" + error + ", result="
				+ result + ", contentType=" + contentType + "]";
	}
	
}

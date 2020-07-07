package com.inovision.commander.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@ApiModel(description = "Describes HTTP host/server object")
@Entity
@Table(name="hosts")
@Data
@EqualsAndHashCode(of = {"id"})
public class Host {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "host_id_seq")
	@SequenceGenerator(name = "host_id_seq", sequenceName = "hosts_id_seq", allocationSize = 1)
	private int id = -1;
	
	@Column(name="name")
	private String name;
	
	@Column(name="hostname")
	private String hostName;
	
	@Column(name="port")
	private int port;
	
	@Column(name="securehttp", columnDefinition = "TINYINT")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean secureHttp;

	@ManyToOne // Since there is no mapped by, Host does not own this relationship
	@JoinColumn(name="test_category_id")
	private Category category;
	
	@Override
	public String toString() {
		return "Host [id=" + id + ", name=" + name + ", hostName=" + hostName + ", port=" + port + ", secureHttp="
				+ secureHttp + ", category=" + category + "]";
	}
	
	public String toUrlFormat() {
		return String.format("%s://%s:%d", secureHttp ? "https" : "http", hostName, port);
	}	
}

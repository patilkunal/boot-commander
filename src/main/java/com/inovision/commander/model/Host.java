package com.inovision.commander.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HOSTS")
public class Host {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id = -1;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="HOSTNAME")
	private String hostName;
	
	@Column(name="PORT")
	private int port;
	
	@Column(name="SECUREHTTP")
	private boolean secureHttp;

	@ManyToOne // Since there is no mapped by, Host does not own this relationship
	@JoinColumn(name="TEST_CATEGORY_ID")
	private Category category;
	
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
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isSecureHttp() {
		return secureHttp;
	}
	public void setSecureHttp(boolean secureHttp) {
		this.secureHttp = secureHttp;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Host [id=" + id + ", name=" + name + ", hostName=" + hostName + ", port=" + port + ", secureHttp="
				+ secureHttp + ", category=" + category + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && (obj instanceof Host) && (this.id == ((Host)obj).id);
	}
	
	@Override
	public int hashCode() {
		return 37 * id;
	}

	public String toUrlFormat() {		
		return String.format("%s://%s:%d", secureHttp ? "https" : "http", hostName, port);
	}	
}

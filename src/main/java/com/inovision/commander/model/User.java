package com.inovision.commander.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="USERS")
@NamedQueries(
	{
		@NamedQuery(name="User.UpdateTokenAccess",query="Update User u set u.tokenAccessDate = ?1 where u.name = ?2"),
		@NamedQuery(name="User.UpdateToken",query="Update User u set u.token = ?1 where u.name = ?2")
	}
)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="USERNAME", unique=true, nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	private String name;
	private String email;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="TOKEN_CREATE_TS")
	private Date tokenCreateDate;

	@Column(name="TOKEN_ACCESS_TS")
	private Date tokenAccessDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<UserRole> roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTokenAccessDate() {
		return tokenAccessDate;
	}
	public void setTokenAccessDate(Date tokenAccessDate) {
		this.tokenAccessDate = tokenAccessDate;
	}
	public Date getTokenCreateDate() {
		return tokenCreateDate;
	}
	public void setTokenCreateDate(Date tokenCreateDate) {
		this.tokenCreateDate = tokenCreateDate;
	}
	public Set<UserRole> getRoles() { return this.roles; }
	public void setRoles(Set<UserRole> roles) { this.roles = roles; }
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", name=" + name + ", email=" + email + "]";
	}
	
}

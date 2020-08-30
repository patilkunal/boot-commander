package com.inovision.commander.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
@NamedQueries(
	{
		@NamedQuery(name="User.UpdateTokenAccess",query="Update User u set u.tokenAccessDate = ?1 where u.name = ?2"),
		@NamedQuery(name="User.UpdateToken",query="Update User u set u.token = ?1 where u.name = ?2")
	}
)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name="user_seq_gen", sequenceName = "users_id_seq")
	private int id;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	private String name;
	private String email;
	
	@Column(name="token")
	private String token;
	
	@Column(name="token_create_ts")
	private Date tokenCreateDate;

	@Column(name="token_access_ts")
	private Date tokenAccessDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<UserRole> roles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		return "User [userName=" + username + ", name=" + name + ", email=" + email + "]";
	}
	
}

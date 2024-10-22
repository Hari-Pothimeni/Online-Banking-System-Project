package org.jpmc.OBS.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "usertype")
    private String usertype;

	public User() {
		super();
		
	}

	public User(String username, String password, String usertype) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", usertype=" + usertype + "]";
	}




}

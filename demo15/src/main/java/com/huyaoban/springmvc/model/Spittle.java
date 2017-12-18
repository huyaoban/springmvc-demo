package com.huyaoban.springmvc.model;

import java.io.Serializable;

public class Spittle implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public Spittle() {

	}

	public Spittle(long id, String username, String password, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "[id=" + id + ",username=" + username + ",password=" + password + ",firstName=" + firstName
				+ ",lastName=" + lastName + "]";
	}

}

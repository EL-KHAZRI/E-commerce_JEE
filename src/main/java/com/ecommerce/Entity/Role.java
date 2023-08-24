package com.ecommerce.Entity;

import java.util.List;

public class Role {
	private int id;
	private String role;
	private List<User> usersList;
	
	public Role() {}
	
	public Role(String role, List<User> usersList) {
		this.role = role;
		this.usersList = usersList;
	}
	
	public Role(int id, String role, List<User> usersList) {
		this.id = id;
		this.role = role;
		this.usersList = usersList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public List<User> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	
}
package com.test.vo;

public class TestVO {

	private String id;
	private String password;
	private String uName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return uName;
	}
	public void setName(String name) {
		this.uName = name;
	}
	
	@Override
	public String toString() {
		return "TestVO [id=" + id + ", password=" + password + ", name=" + uName + "]";
	}
}

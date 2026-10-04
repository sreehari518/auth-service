package com.ecommerce.auth.dto;

public class LoginResponse {

	private String name;
	private String email;

	private String token;

	public LoginResponse() {
		
	}

	public LoginResponse(String name, String email, String token) {
		super();
		this.name = name;
		this.email = email;
		this.token = token;
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

	@Override
	public String toString() {
		return "LoginResponse [name=" + name + ", email=" + email + ", token=" + token + ", getName()=" + getName()
				+ ", getEmail()=" + getEmail() + ", getToken()=" + getToken() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}

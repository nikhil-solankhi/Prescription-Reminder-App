package com.app.dto;

public class LoginRequestDto {
	private String email;
	private String password;

	public LoginRequestDto() {
	}

	public LoginRequestDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequestDto [email=" + email + ", password=" + password + "]";
	}

}

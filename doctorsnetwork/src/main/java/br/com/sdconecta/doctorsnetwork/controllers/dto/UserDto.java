package br.com.sdconecta.doctorsnetwork.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

	@NotBlank
	@Size(max = 255)
	public String email;
	
	@NotBlank
	@Size(max = 255)
	public String password;
	
	@NotBlank
	@Size(max = 255)
	public String name;
	
	@NotBlank
	@Size(max = 255)
	public String surname;
	
	@NotBlank
	@Size(max = 255)
	public String mobilePhone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}

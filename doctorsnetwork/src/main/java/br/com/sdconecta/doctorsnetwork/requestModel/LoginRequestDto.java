package br.com.sdconecta.doctorsnetwork.requestModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequestDto {

	@NotBlank
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	private String password;

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
}

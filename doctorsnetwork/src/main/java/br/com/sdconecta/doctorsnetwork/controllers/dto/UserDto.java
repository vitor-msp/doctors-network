package br.com.sdconecta.doctorsnetwork.controllers.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

	@NotBlank
	@Size(max = 255)
	@Column(unique = true)
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
}

package br.com.sdconecta.doctorsnetwork.requestModel;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSDConectaDto {

	@NotBlank
	@Size(max = 255)
	public String email;
	
	@NotBlank
	@Size(max = 255)
	public String name;
	
	@NotBlank
	@Size(max = 255)
	public String surname;
	
	@NotBlank
	@Size(max = 255)
	public String mobile_phone;
	
//	@Valid
//	public List<CrmDto> crms;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return mobile_phone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobile_phone = mobilePhone;
	}
	
//	public List<CrmDto> getCrms() {
//		return crms;
//	}
//
//	public void setCrms(List<CrmDto> crms) {
//		this.crms = crms;
//	}
}

package br.com.sdconecta.doctorsnetwork.requestModel;

import java.util.ArrayList;
import java.util.List;

import br.com.sdconecta.doctorsnetwork.domain.Crm;

public class UserSDConectaDto {

	public String email;
	
	public String name;
	
	public String surname;
	
	public String mobile_phone;
	
	public List<CrmDto> crms;
	
	public UserSDConectaDto() {}

	public UserSDConectaDto(String email, String name, String surname, String mobile_phone) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.mobile_phone = mobile_phone;
	}

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
	
	public List<CrmDto> getCrms() {
		return crms;
	}

	public void setCrms(List<CrmDto> crms) {
		this.crms = crms;
	}
	
	public void setCrmsFromEntity(List<Crm> crms) {
		
		List<CrmDto> crmsDto = new ArrayList<>();
		
		crms.forEach(crm -> {
			crmsDto.add(new CrmDto(crm.getCrm(), crm.getUf(), crm.getSpecialty()));
		});
		
		this.crms = crmsDto;
	}
}

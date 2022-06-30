package br.com.sdconecta.doctorsnetwork.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CrmDto {

	@NotBlank
	@Size(max = 45)
	public String crm;
	
	@NotBlank
	@Size(max = 2)
	public String uf;
	
	@Size(max = 255)
	public String specialty;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}

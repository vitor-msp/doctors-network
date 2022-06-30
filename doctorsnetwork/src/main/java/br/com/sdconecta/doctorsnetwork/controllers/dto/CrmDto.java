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
}

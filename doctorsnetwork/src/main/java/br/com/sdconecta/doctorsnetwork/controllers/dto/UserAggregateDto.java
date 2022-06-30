package br.com.sdconecta.doctorsnetwork.controllers.dto;

import java.util.List;

import javax.validation.constraints.Size;

public class UserAggregateDto {

	private UserDto user;
	
	@Size(min = 0)
	private List<CrmDto> crms;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<CrmDto> getCrms() {
		return crms;
	}

	public void setCrms(List<CrmDto> crms) {
		this.crms = crms;
	}
}

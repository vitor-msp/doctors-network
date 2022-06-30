package br.com.sdconecta.doctorsnetwork.controllers.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserAggregateDto {

	@Valid
	@NotNull
	private UserDto user;
	
	@Valid
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

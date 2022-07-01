package br.com.sdconecta.doctorsnetwork.requestModel;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserAggregateDto {

	@Valid
	@NotNull
	public UserDto user;
	
	@Valid
	public List<CrmDto> crms;
	
	public UserAggregateDto() {
		crms = new ArrayList<>();
	}

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

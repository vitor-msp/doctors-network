package br.com.sdconecta.doctorsnetwork.requestModel;

import javax.validation.constraints.NotBlank;

public class BroadcastMsgDto {

	@NotBlank
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

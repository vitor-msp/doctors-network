package br.com.sdconecta.doctorsnetwork.responseModel;

public class LoginResponseDto {

	private String jwt;
	
	public LoginResponseDto(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}

package br.com.sdconecta.doctorsnetwork.responseModel;

public class LoginResponseDto {

	private String jwt;
	
	private SDConectaUserTokenResponseDto SDConecta;
	
	public LoginResponseDto(String jwt, SDConectaUserTokenResponseDto SDConecta) {
		this.jwt = jwt;
		this.SDConecta = SDConecta;
	}

	public String getJwt() {
		return jwt;
	}

	public SDConectaUserTokenResponseDto getSDConecta() {
		return SDConecta;
	}
}

package br.com.sdconecta.doctorsnetwork.responseModel;

public class LoginResponseDto {

	private String jwt;
	
	private ResponseSDConectaDto SDConecta;
	
	public LoginResponseDto(String jwt, ResponseSDConectaDto SDConecta) {
		this.jwt = jwt;
		this.SDConecta = SDConecta;
	}

	public String getJwt() {
		return jwt;
	}

	public ResponseSDConectaDto getSDConecta() {
		return SDConecta;
	}
}

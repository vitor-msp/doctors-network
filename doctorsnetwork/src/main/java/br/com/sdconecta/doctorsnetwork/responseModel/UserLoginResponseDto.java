package br.com.sdconecta.doctorsnetwork.responseModel;

public class UserLoginResponseDto extends LoginResponseDto {

	private SDConectaUserTokenResponseDto SDConecta;
	
	public UserLoginResponseDto(String jwt, SDConectaUserTokenResponseDto SDConecta) {
		super(jwt);
		this.SDConecta = SDConecta;
	}

	public SDConectaUserTokenResponseDto getSDConecta() {
		return SDConecta;
	}
}

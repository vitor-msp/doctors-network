package br.com.sdconecta.doctorsnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.sdconecta.doctorsnetwork.requestModel.LoginRequestDto;
import br.com.sdconecta.doctorsnetwork.requestModel.UserSDConectaDto;
import br.com.sdconecta.doctorsnetwork.responseModel.LoginResponseDto;
import br.com.sdconecta.doctorsnetwork.responseModel.ResponseSDConectaDto;
import br.com.sdconecta.doctorsnetwork.security.jwt.JwtUtils;
//import br.com.sdconecta.doctorsnetwork.security.services.UserDetailsImpl;

@Service
public class LoginService {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	public LoginResponseDto execute(LoginRequestDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    ResponseEntity<ResponseSDConectaDto> sdconecta = loginSDConecta();
	    
//	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    
	    return new LoginResponseDto(jwt, sdconecta.getBody());
	};
	
	private ResponseEntity<ResponseSDConectaDto> loginSDConecta() {
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "https://beta.sdconecta.com/api/v2/partners/generate-user-token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI2NTVjNWNiNC05MDZiLTRiOTAtYTY2Yi1jZGJiY2IyNDY3MTciLCJhdWQiOiI2NTVjNWNiNC05MDZiLTRiOTAtYTY2Yi1jZGJiY2IyNDY3MTciLCJuYmYiOjE2NTY5ODM5OTEsInNjb3BlIjpbInBhcnRuZXIiXSwiaXNzIjoiaHR0cHM6XC9cL2JldGEuc2Rjb25lY3RhLmNvbSIsImV4cCI6MTY1NzA3MDM5MSwiaWF0IjoxNjU2OTgzOTkxfQ.Hn2b31WIXrQJvXBc9XpVJCk9W8I-ChtQwq5ArulyIHCl0-O8ty4KstWp3AgEA9Hmm03Dq1DJCsrL0UfLf0Kn-TjkS4BLXWCAn46zizoprCf1ba9zKPUnRBPYbCxi8l9Vu5ctTYUW0sTgE-wGqZx-Z_l_0_DfKbiPlBM_qzT7Gz_jB00jpT9hhoyKlv2E_9f6i9rOulRxTTeeGwxWoFyUFoobVk3xtjX4HObuCU7gaxN5BiOSTBfI9OMVbCU9B7ew2IPzdXw7Z4H-hv9VOH-LnIOcU9hNMUgIowZFxSfbpXxV4wD5yEUlIutw597DgC2J80PbfcWpqgCSetnhdiZ7lA");
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("nome", "João Silva");
//		map.add("valor", new BigDecimal("1.00"));
		
		UserSDConectaDto userSDConecta = new UserSDConectaDto();
		userSDConecta.setEmail("usuario.teste-1@email.com");
		userSDConecta.setMobilePhone("12345678");
		userSDConecta.setName("teste");
		userSDConecta.setSurname("fulano");

		HttpEntity<UserSDConectaDto> request = new HttpEntity<UserSDConectaDto>(userSDConecta, headers);
		
		ResponseEntity<ResponseSDConectaDto> response = restTemplate.postForEntity(uri, request, ResponseSDConectaDto.class);

		return response;
	}
}

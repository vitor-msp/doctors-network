package br.com.sdconecta.doctorsnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;
import br.com.sdconecta.doctorsnetwork.requestModel.UserSDConectaDto;
import br.com.sdconecta.doctorsnetwork.responseModel.UserLoginResponseDto;
import br.com.sdconecta.doctorsnetwork.responseModel.SDConectaUserTokenResponseDto;
import br.com.sdconecta.doctorsnetwork.responseModel.SDConectaTokenResponseDto;

@Service
public class UserLoginService {
	
	@Autowired
	private UsersRepository usersRepository;
	
    @Value("${doctors-network.sd-client-id}")
    private String sdClientId;

    @Value("${doctors-network.sd-client-secret}")
    private String sdClientSecret;
    
    private User user;
	
	UserLoginResponseDto execute(String email, String jwt){
		
	    this.user = usersRepository.findByEmail(email).get();
	    
	    ResponseEntity<SDConectaTokenResponseDto> sdConectaToken = getSDConectaToken();
	    
	    ResponseEntity<SDConectaUserTokenResponseDto> sdConectaUserToken = generateSDConectaUserToken(
	    		sdConectaToken.getBody().getAccess_token());

	    updateUserAuthorizationStatus(sdConectaUserToken.getBody().getAuthorization_status());
	    
	    return new UserLoginResponseDto(jwt, sdConectaUserToken.getBody());
	};
	
	private ResponseEntity<SDConectaTokenResponseDto> getSDConectaToken() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "https://beta.sdconecta.com/oauth2/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", sdClientId);
		map.add("client_secret", sdClientSecret);
		map.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		return restTemplate.postForEntity(uri, request, SDConectaTokenResponseDto.class);
	}
	
	private ResponseEntity<SDConectaUserTokenResponseDto> generateSDConectaUserToken(String bearerToken) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "https://beta.sdconecta.com/api/v2/partners/generate-user-token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(bearerToken);
		
		UserSDConectaDto userSDConecta = new UserSDConectaDto(
				user.getEmail(),user.getName(), user.getSurname(), user.getMobilePhone());
		
		if(!user.getCrms().isEmpty()) {
			userSDConecta.setCrmsFromEntity(user.getCrms());
		}

		HttpEntity<UserSDConectaDto> request = new HttpEntity<>(userSDConecta, headers);
		
		return restTemplate.postForEntity(uri, request, SDConectaUserTokenResponseDto.class);
	}
	
	private void updateUserAuthorizationStatus(String authorizationStatus) {
		
		user.setLastSDConectaAuthorizationStatus(authorizationStatus);
		
		usersRepository.save(user);
	}
}

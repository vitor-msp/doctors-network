package br.com.sdconecta.doctorsnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.Role;
import br.com.sdconecta.doctorsnetwork.requestModel.LoginRequestDto;
import br.com.sdconecta.doctorsnetwork.responseModel.AdminLoginResponseDto;
import br.com.sdconecta.doctorsnetwork.responseModel.LoginResponseDto;
import br.com.sdconecta.doctorsnetwork.security.jwt.JwtUtils;
import br.com.sdconecta.doctorsnetwork.security.services.UserDetailsImpl;

@Service
public class LoginService {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    private UserLoginService userLoginService;
    
	@Autowired
	private JwtUtils jwtUtils;
    
	public LoginResponseDto execute(LoginRequestDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    
	    String role = userDetails.getAuthorities().toArray()[0].toString();
	    
	    switch (Role.valueOf(role)) {
	    	case ADMIN: {
	    		return new AdminLoginResponseDto(jwt);
	    	}

	    	case USER:			
	    	default:
	    		return userLoginService.execute(userDetails.getEmail(), jwt);
		}
	};
}

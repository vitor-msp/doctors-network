package br.com.sdconecta.doctorsnetwork.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.requestModel.LoginRequestDto;
import br.com.sdconecta.doctorsnetwork.responseModel.LoginResponseDto;
import br.com.sdconecta.doctorsnetwork.services.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginReqDto){
		
		try {
			
			LoginResponseDto loginResDto = loginService.execute(loginReqDto);
			
			return new ResponseEntity<LoginResponseDto>(loginResDto, HttpStatus.OK);
			
		} catch (RuntimeException e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

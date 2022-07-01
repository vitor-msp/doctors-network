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

import br.com.sdconecta.doctorsnetwork.controllers.dto.UserAggregateDto;
import br.com.sdconecta.doctorsnetwork.services.CreateUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CreateUserController {

	@Autowired
	private CreateUserService createUserService;
	
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody UserAggregateDto userAggrDto){
		
		try {
			
			createUserService.execute(userAggrDto);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (RuntimeException e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

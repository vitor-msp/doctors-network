package br.com.sdconecta.doctorsnetwork.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.services.GetAllUsersService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class GetAllUsersController {

	@Autowired
	private GetAllUsersService getAllUsersService;
	
	@GetMapping("/usersAll")
	public ResponseEntity<?> get(
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String specialty){
		
		try {
			
			List<User> users = getAllUsersService.execute(name, specialty);
			
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			
		} catch (RuntimeException e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

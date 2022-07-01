package br.com.sdconecta.doctorsnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.services.GetUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class GetUserController {

	@Autowired
	private GetUserService getUserService;
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id){
		
		try {
			
			User user = getUserService.execute(id);
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		} catch (RuntimeException e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

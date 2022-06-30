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
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String name){
		
		try {
			
			return new ResponseEntity<List<User>>(usersRepository.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

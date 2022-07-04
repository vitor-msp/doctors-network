package br.com.sdconecta.doctorsnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.services.DeleteUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class DeleteUserController {

	@Autowired
	private DeleteUserService deleteUserService;
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		try {
			
			deleteUserService.execute(id);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (EmptyResultDataAccessException e) {
			
			return new ResponseEntity<String>("User not exists", HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

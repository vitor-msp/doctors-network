package br.com.sdconecta.doctorsnetwork.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.requestModel.UserAggregateDto;
import br.com.sdconecta.doctorsnetwork.services.EditUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EditUserController {

	@Autowired
	private EditUserService editUserService;
	
	@PutMapping("/users")
	public ResponseEntity<?> edit(
			@RequestParam(required = true) Integer id,
			@Valid @RequestBody UserAggregateDto userAggrDto){
		
		try {
			
			editUserService.execute(id, userAggrDto);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (RuntimeException e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}
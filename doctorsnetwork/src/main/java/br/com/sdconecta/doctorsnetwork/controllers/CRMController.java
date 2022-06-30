package br.com.sdconecta.doctorsnetwork.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.domain.CRM;
import br.com.sdconecta.doctorsnetwork.repositories.CRMRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CRMController {

	@Autowired
	private CRMRepository crmRepository;
	
	@GetMapping("/crm")
	public ResponseEntity<List<CRM>> getAll(){
		
		try {
			
			return new ResponseEntity<List<CRM>>(crmRepository.findAll(), HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

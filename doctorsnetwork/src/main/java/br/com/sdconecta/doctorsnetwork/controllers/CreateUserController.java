package br.com.sdconecta.doctorsnetwork.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdconecta.doctorsnetwork.controllers.dto.CrmDto;
import br.com.sdconecta.doctorsnetwork.controllers.dto.UserAggregateDto;
import br.com.sdconecta.doctorsnetwork.controllers.dto.UserDto;
import br.com.sdconecta.doctorsnetwork.domain.Crm;
import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CreateUserController {

	@Autowired
	private UsersRepository usersRepository;
	
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody UserAggregateDto userAggrDto){
		
		try {
			
			UserDto userDto = userAggrDto.getUser();
			
			User user = new User(
					userDto.email, userDto.password, userDto.name, userDto.surname, userDto.mobilePhone);
			
			ArrayList<Crm> crms = new ArrayList<>();
			
			for (CrmDto crmDto: userAggrDto.getCrms()) {
				crms.add(new Crm(crmDto.crm, crmDto.uf, crmDto.specialty, user));
			}
			
			user.setCrms(crms);
			
			usersRepository.save(user);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {

			return new ResponseEntity<Exception>(e ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};
}

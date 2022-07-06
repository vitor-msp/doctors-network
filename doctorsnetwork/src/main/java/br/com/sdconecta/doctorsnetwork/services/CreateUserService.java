package br.com.sdconecta.doctorsnetwork.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.Crm;
import br.com.sdconecta.doctorsnetwork.domain.Role;
import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;
import br.com.sdconecta.doctorsnetwork.requestModel.CrmDto;
import br.com.sdconecta.doctorsnetwork.requestModel.UserAggregateDto;
import br.com.sdconecta.doctorsnetwork.requestModel.UserDto;

@Service
public class CreateUserService {
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UsersRepository usersRepository;
	
	public void execute(UserAggregateDto userAggrDto){
		
		UserDto userDto = userAggrDto.getUser();
		
		if(usersRepository.existsByEmail(userDto.email)) {
			throw new RuntimeException("Email already in use");
		}
		
		User user = new User(
				userDto.email, encoder.encode(userDto.password),
				userDto.name, userDto.surname, userDto.mobilePhone);
		
		user.setRole(Role.USER);
		
		if(!userAggrDto.crms.isEmpty()) {
		
			ArrayList<Crm> crms = new ArrayList<>();
			
			for (CrmDto crmDto: userAggrDto.getCrms()) {
				crms.add(new Crm(crmDto.crm, crmDto.uf, crmDto.specialty, user));
			}
		
			user.setCrms(crms);
		}
		
		usersRepository.save(user);
	};
}

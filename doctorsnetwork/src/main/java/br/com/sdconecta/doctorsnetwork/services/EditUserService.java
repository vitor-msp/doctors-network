package br.com.sdconecta.doctorsnetwork.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.Crm;
import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;
import br.com.sdconecta.doctorsnetwork.requestModel.CrmDto;
import br.com.sdconecta.doctorsnetwork.requestModel.UserAggregateDto;
import br.com.sdconecta.doctorsnetwork.requestModel.UserDto;

@Service
public class EditUserService {

	@Autowired
	private UsersRepository usersRepository;
	
	public void execute(Integer id, UserAggregateDto userAggrDto){
		
		Optional<User> optUser = usersRepository.findById(id);
		
		if(optUser.isEmpty()) {
			throw new RuntimeException("User not exists");
		}
		
		UserDto userDto = userAggrDto.getUser();
		
		Optional<User> optUserWithEmail = usersRepository.findByEmail(userDto.email);
		
		if(optUserWithEmail.isPresent() && (optUserWithEmail.get().getId() != id)) {
			throw new RuntimeException("Email already in use");
		}
		
		User user = optUser.get();
		user.setEmail(userDto.email);
		user.setPassword(new BCryptPasswordEncoder().encode(userDto.password));
		user.setName(userDto.name);
		user.setSurname(userDto.surname);
		user.setMobilePhone(userDto.mobilePhone);
		
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

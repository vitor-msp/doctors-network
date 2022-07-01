package br.com.sdconecta.doctorsnetwork.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		if(!usersRepository.existsById(id)) {
			throw new RuntimeException("User not exists");
		}
		
		UserDto userDto = userAggrDto.getUser();
		
		User user = new User(
				userDto.email, userDto.password, userDto.name, userDto.surname, userDto.mobilePhone);
		
		user.setId(id);
		
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

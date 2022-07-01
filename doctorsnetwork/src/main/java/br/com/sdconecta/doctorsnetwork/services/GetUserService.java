package br.com.sdconecta.doctorsnetwork.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Service
public class GetUserService {

	@Autowired
	private UsersRepository usersRepository;
	
	public User execute(Integer id){
		
		Optional<User> optUser = usersRepository.findById(id);

		if(optUser.isEmpty()) {
			throw new RuntimeException("User not exists");
		}
		
		return optUser.get()
;	};
}

package br.com.sdconecta.doctorsnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Service
public class GetAllUsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	public List<User> execute(String name, String specialty){
		
		return usersRepository.findDistinctByNameContainingAndCrms_SpecialtyContaining(name, specialty);

	};
}

package br.com.sdconecta.doctorsnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Service
public class DeleteUserService {

	@Autowired
	private UsersRepository usersRepository;
	
	public void execute(Integer id){
		
		usersRepository.deleteById(id);

	};
}

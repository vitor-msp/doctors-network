package br.com.sdconecta.doctorsnetwork.services;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Service
public class BroadcastService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private NotificationServiceImpl notificationService;
	
	public void execute(String message){
		
		Pageable page = null;
		int qttPage = 2;
		int numberPage = 0;
		long totalUsers = usersRepository.count();
		long counter = 0;
		
		while(counter <= totalUsers) {
		
			page = PageRequest.of(numberPage++, qttPage);
			
			Stream<User> users = usersRepository.findAll(page).get();
			
			users.forEach((u) -> {
				notificationService.notify(u, message);
			});

			counter += qttPage;
		}		
	};
}

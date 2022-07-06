package br.com.sdconecta.doctorsnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.sdconecta.doctorsnetwork.domain.Role;
import br.com.sdconecta.doctorsnetwork.domain.User;
import br.com.sdconecta.doctorsnetwork.repositories.UsersRepository;

@Component
public class SaveAdminUser implements ApplicationRunner {

	@Autowired
    private UsersRepository usersRepository;

    public void run(ApplicationArguments args) {
        User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"),
    			"admin", "user","1234");

        admin.setRole(Role.ADMIN);
    	
    	usersRepository.save(admin);
    }
}
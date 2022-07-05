package br.com.sdconecta.doctorsnetwork.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.doctorsnetwork.domain.User;

public interface UsersRepository extends JpaRepository<User, Integer>{
	
	Boolean existsByEmail(String email);
	
	List<User> findDistinctByNameContainingAndCrms_SpecialtyContaining(String name, String specialty);
	
	Optional<User> findByEmail(String email);
}

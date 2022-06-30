package br.com.sdconecta.doctorsnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.doctorsnetwork.domain.User;

public interface UsersRepository extends JpaRepository<User, Integer>{

}

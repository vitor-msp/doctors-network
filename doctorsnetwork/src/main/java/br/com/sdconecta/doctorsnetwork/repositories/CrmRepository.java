package br.com.sdconecta.doctorsnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.doctorsnetwork.domain.Crm;

public interface CrmRepository extends JpaRepository<Crm, Integer>{

}

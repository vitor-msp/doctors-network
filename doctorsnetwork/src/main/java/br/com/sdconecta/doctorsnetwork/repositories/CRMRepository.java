package br.com.sdconecta.doctorsnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.doctorsnetwork.domain.CRM;

public interface CRMRepository extends JpaRepository<CRM, Integer>{

}

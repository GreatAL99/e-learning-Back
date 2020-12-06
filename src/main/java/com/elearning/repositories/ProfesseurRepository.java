package com.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elearning.entities.Professeur;

@RepositoryRestResource
public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{

	
	public Professeur findByEmail(String email);
}

package com.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elearning.entities.Etudiant;


@RepositoryRestResource
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	public Etudiant findByEmail(String email);

}

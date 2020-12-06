package com.elearning.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elearning.entities.Utilisateur;

@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public List<Utilisateur> findByEmail(String Email);
	
	@Query(value="select * from utilisateur where username like :x limit 1",nativeQuery = true)
	public Utilisateur findByUsername(@Param("x") String email);
}

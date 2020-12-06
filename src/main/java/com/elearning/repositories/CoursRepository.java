package com.elearning.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elearning.entities.Categorie;
import com.elearning.entities.Cours;


@RepositoryRestResource
public interface CoursRepository extends JpaRepository<Cours, Long> {

	List<Cours> findByNomContaining( String keyword);

	List<Cours> findByCategorie(Categorie categorie);

}

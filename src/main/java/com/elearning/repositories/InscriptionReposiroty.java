package com.elearning.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elearning.entities.Inscription;



public interface InscriptionReposiroty extends JpaRepository<Inscription, Long>{

	@Query(value="select * from inscription where cour_id like :x", nativeQuery = true)
	public List<Inscription> findSubscribedStudentsIds(@Param("x") Long coursId);

	@Query(value="select * from inscription where etudiant_id  like :y and cour_id like :x", nativeQuery = true)
	public Inscription findByEtudiantIdAndCourId(@Param("x") Long id, @Param("y") Long iduser);

}

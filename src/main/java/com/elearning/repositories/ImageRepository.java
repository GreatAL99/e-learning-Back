package com.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elearning.entities.Image;



@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image, Long> {

	
}

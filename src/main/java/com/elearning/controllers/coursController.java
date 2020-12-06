package com.elearning.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.elearning.entities.Categorie;
import com.elearning.entities.Cours;
import com.elearning.entities.Etudiant;
import com.elearning.entities.Image;
import com.elearning.entities.Inscription;
import com.elearning.entities.Professeur;
import com.elearning.repositories.CoursRepository;
import com.elearning.repositories.InscriptionReposiroty;



@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/courses")
public class coursController {
	@Autowired
	private CoursRepository coursRepository;
	@Autowired
	private InscriptionReposiroty inscriptionRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${url.users}")
	private String userUrl;
	
	@Value("${url.images}")
	private String imageUrl;
	

	@GetMapping("/{keyword}")
	public List<Cours> getCourseByKeyword(@PathVariable("keyword") String keyword) {
//		if(Enums.getIfPresent(Categorie.class, StringUtils.capitalize(keyword)).isPresent()) {
//			
//			Categorie categorie = Categorie.valueOf(StringUtils.capitalize(keyword));
//			return coursRepository.findByCategorie(categorie);
//		 } 
		return coursRepository.findByNomContaining(keyword);
	}
	
	@GetMapping("/All")
	public List<Cours> getAllCourse() {
		return coursRepository.findAll();
	}
	
	@GetMapping("/inscription/All")
	public List<Inscription> getSubscriptionsByCourse() {
		return inscriptionRepository.findAll();
	}
	
	
	@PostMapping(value="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addCours(@RequestHeader("Authorization") String token ,@RequestPart(value="image",required = false) MultipartFile image,
			@RequestPart("cours") Cours cours,@RequestPart("professeur") String profUsername) throws RestClientException, IOException {
		
		Professeur prof = restTemplate.getForObject(userUrl+"professor/"+profUsername, Professeur.class);
		
		if(image!=null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, token);
			HttpEntity< Image > imgEntity = new HttpEntity<>(new Image(image.getOriginalFilename(),image.getContentType(),image.getBytes()), headers);

			Image img = restTemplate.postForObject("http://service-image/images/addImage",imgEntity,Image.class);
			coursRepository.save(new Cours(cours.getNom(), cours.getCategorie(), cours.getDateDeb(),
					cours.getDateFin(),cours.getDescription(), prof.getIduser(), img.getId()));
		}
		else {
			coursRepository.save(new Cours(cours.getNom(), cours.getCategorie(), cours.getDateDeb(),
					cours.getDateFin(),cours.getDescription(), prof.getIduser()));
		}

		
		
		return "Course added";
	}
	
	@PutMapping(value="/update/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@PathVariable("id") Long id,
			@RequestPart(value="image",required = false) MultipartFile image,
			@RequestPart("cours") Cours cours,@RequestPart("professeur") String profUsername) throws IOException {
		
		Cours coursToUpdate = coursRepository.findById(id).get();
		
		coursToUpdate.setCategorie(cours.getCategorie());
		coursToUpdate.setNom(cours.getNom());
		coursToUpdate.setDateDeb(cours.getDateDeb());
		coursToUpdate.setDateFin(cours.getDateFin());
		coursToUpdate.setDescription(cours.getDescription());
		
		if(image!=null) {
			Image imgEntity= null;
			if(coursToUpdate.getImageId() != null) {
				 imgEntity = new Image(coursToUpdate.getImageId(),image.getOriginalFilename(),
						 image.getContentType(),image.getBytes());

			}
			else {
				 imgEntity = new Image(image.getOriginalFilename(),image.getContentType(),image.getBytes());	
			}

			Image img = restTemplate.postForObject(imageUrl+"addImage",imgEntity,Image.class);
			
			coursToUpdate.setImageId(img.getId());
		}
		else {
			coursToUpdate.setImageId(coursToUpdate.getImageId());
		}
		coursRepository.save(coursToUpdate);		
		return "MAJ réussie";
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Long id,@RequestHeader("Authorization") String token ) {
		
		Long imageId =coursRepository.findById(id).get().getImageId();
		
		if(imageId != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, token);
			HttpEntity<?> request = new HttpEntity<Object>(headers);
			restTemplate.exchange(imageUrl+"delete/"+imageId,HttpMethod.DELETE, request, String.class);
		}
		
		
		coursRepository.deleteById(id);
		
		return "suppression réussie";
	}
	
	@PostMapping(value="/subscribe/{username}/{id}")
	public String subscribe(@PathVariable("id") Long id,@PathVariable("username") String username ){

		Etudiant etudiant = restTemplate.getForObject(userUrl+"student/"+username, Etudiant.class);
		Cours cours = coursRepository.findById(id).get();
		Inscription inscription = new Inscription(etudiant.getIduser(), cours);
		
		inscriptionRepository.save(inscription);
		
		return "Inscription réussie";
	}
	
	@DeleteMapping(value="/unsubscribe/{username}/{id}")
	public String unsubscribe(@PathVariable("id") Long id,@PathVariable("username") String username ){

		Etudiant etudiant = restTemplate.getForObject(userUrl+"student/"+username, Etudiant.class);
		Cours cours = coursRepository.findById(id).get();
		
		inscriptionRepository.delete(inscriptionRepository.findByEtudiantIdAndCourId(cours.getId(), etudiant.getIduser()));
		
		return "Desinscription réussie";
	}

}

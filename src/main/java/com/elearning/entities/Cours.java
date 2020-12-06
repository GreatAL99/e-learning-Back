package com.elearning.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cours {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private Categorie categorie;
	private LocalDate dateDeb;
	private LocalDate dateFin;
	private String description;
	
	private Long professeurId;
	private Long imageId;


	public Cours(String nom, Categorie categorie, LocalDate dateDeb, LocalDate dateFin, String description,
			 Long professeurId, Long imageId) {
		this.nom = nom;
		this.categorie = categorie;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.description = description;
		this.professeurId = professeurId;
		this.imageId = imageId;
	}


	public Cours(String nom, Categorie categorie, LocalDate dateDeb, LocalDate dateFin, String description,
			 Long professeurId) {
		this.nom = nom;
		this.categorie = categorie;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.description = description;
		this.professeurId = professeurId;
	}


	public Cours() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public LocalDate getDateDeb() {
		return dateDeb;
	}


	public void setDateDeb(LocalDate dateDeb) {
		this.dateDeb = dateDeb;
	}


	public LocalDate getDateFin() {
		return dateFin;
	}


	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}



	public Long getProfesseurId() {
		return professeurId;
	}


	public void setProfesseurId(Long professeurId) {
		this.professeurId = professeurId;
	}


	public Long getImageId() {
		return imageId;
	}


	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}

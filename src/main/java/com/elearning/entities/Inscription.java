package com.elearning.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Inscription {
	
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate dateInscription;
	private Long etudiantId;
	
	@OneToOne
	private Cours cour;

	public Inscription(Long etudiantId, Cours courId) {
		this.dateInscription = LocalDate.now();
		this.etudiantId = etudiantId;
		this.cour = courId;
	}

	public Inscription() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Long getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(Long etudiantId) {
		this.etudiantId = etudiantId;
	}

	public Cours getCourId() {
		return cour;
	}

	public void setCourId(Cours courId) {
		this.cour = courId;
	}
	
	

}

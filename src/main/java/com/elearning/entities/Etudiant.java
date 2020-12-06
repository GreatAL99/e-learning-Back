package com.elearning.entities;

import java.time.LocalDate;

import javax.persistence.Entity;


@Entity 

public class Etudiant extends Utilisateur{
	
	private Long idimage;
	
	public Etudiant(Utilisateur user) {
		super(user.getIduser(), user.getEmail(), user.getPassword(), "ROLE_ETUDIANT", user.getNom(), user.getPrenom(),
				user.getAdresse(), user.getTel(), user.getDateInscrip());
	}
	
	public Etudiant(Utilisateur user, Long Image) {
		super(user.getIduser(), user.getEmail(), user.getPassword(), "ROLE_ETUDIANT", user.getNom(), user.getPrenom(),
				user.getAdresse(), user.getTel(), user.getDateInscrip());
		this.idimage = Image;
	}

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(Long iduser, String email, String password, String role, String nom, String prenom, String adresse,
			String tel, LocalDate dateInscrip) {
		super(iduser, email, password, role, nom, prenom, adresse, tel, dateInscrip);
		// TODO Auto-generated constructor stub
	}

	public Long getIdimage() {
		return idimage;
	}

	public void setIdimage(Long idimage) {
		this.idimage = idimage;
	}
	
	
	
	
}

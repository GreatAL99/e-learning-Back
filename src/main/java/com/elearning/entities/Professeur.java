package com.elearning.entities;

import java.time.LocalDate;

import javax.persistence.Entity;



@Entity 
public class Professeur extends Utilisateur{
	
	private Long idimage;
	
	public Professeur(Utilisateur user,Long idimage) {
		super(user.getIduser(), user.getEmail(), user.getPassword(), "ROLE_PROFESSEUR", user.getNom(), user.getPrenom(),
				user.getAdresse(), user.getTel(), user.getDateInscrip());
		this.idimage = idimage;
	}
	
	public Professeur(Utilisateur user) {
		super(user.getIduser(), user.getEmail(), user.getPassword(), "ROLE_PROFESSEUR", user.getNom(), user.getPrenom(),
				user.getAdresse(), user.getTel(), user.getDateInscrip());
	}
	
	public Professeur(Long idimage) {
		this.idimage = idimage;
	}
	
	

	public Professeur() {
	}

	public Professeur(Long iduser, String email, String password, String role, String nom, String prenom,
			String adresse, String tel, LocalDate dateInscrip) {
		super(iduser, email, password, role, nom, prenom, adresse, tel, dateInscrip);
	}

	public Long getIdimage() {
		return idimage;
	}

	public void setIdimage(Long idimage) {
		this.idimage = idimage;
	}

	@Override
	public String toString() {
		return "Professeur [idimage=" + idimage + ", getIduser()=" + getIduser() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getRole()=" + getRole() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getAdresse()=" + getAdresse() + ", getTel()=" + getTel()
				+ ", getDateInscrip()=" + getDateInscrip() + "]";
	}

	
	

}

package com.elearning.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;




@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilisateur {
	
	@Id   
	@GeneratedValue
	private Long iduser; 
	@Column(name = "username", unique = true)
	private String email;
	private String password;
	private String role;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;	
	private LocalDate DateInscrip = LocalDate.now();
	
	
	
	
	public Utilisateur() {
	}
	public Utilisateur(Long iduser, String email, String password, String role, String nom, String prenom,
			String adresse, String tel, LocalDate dateInscrip) {
		this.iduser = iduser;
		this.email = email;
		this.password = password;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		DateInscrip = dateInscrip;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public LocalDate getDateInscrip() {
		return DateInscrip;
	}
	public void setDateInscrip(LocalDate dateInscrip) {
		DateInscrip = dateInscrip;
	}
	@Override
	public String toString() {
		return "Utilisateur [iduser=" + iduser + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", DateInscrip="
				+ DateInscrip + "]";
	}
	public void setDetails(String adresse2, LocalDate dateInscrip2, String email2, String nom2, String prenom2,
			String tel2) {
		this.email = email2;
		this.nom = nom2;
		this.prenom = prenom2;
		this.adresse = adresse2;
		this.tel = tel2;
		this.DateInscrip = dateInscrip2;
		
	}
	
	
	
	
}

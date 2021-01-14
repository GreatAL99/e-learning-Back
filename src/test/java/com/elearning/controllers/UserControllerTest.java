package com.elearning.controllers;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import com.elearning.entities.Etudiant;
import com.elearning.entities.Professeur;
import com.elearning.entities.Utilisateur;
import com.elearning.repositories.EtudiantRepository;
import com.elearning.repositories.ProfesseurRepository;
import com.elearning.repositories.UtilisateurRepository;

public class UserControllerTest {
	
	private EtudiantRepository mockedstudentrepository = Mockito.mock(EtudiantRepository.class);
	private ProfesseurRepository mockedprofessorrepository = Mockito.mock(ProfesseurRepository.class);
	private UtilisateurRepository mockeduserrepository = Mockito.mock(UtilisateurRepository.class);
	private UserController mockedusercontroller = Mockito.mock(UserController.class);

	@Test
	@DisplayName("Test wouldnt pass since user already exists!")
	public void addnewusertestalreadyexists() throws IOException {
		
		UserController usercontroller = new UserController(mockedstudentrepository,mockedprofessorrepository,mockeduserrepository);

		Utilisateur olduser = new Utilisateur(100L, "ahmedChoukri@gmail.com", "koronesuki", "ROLE_ETUDIANT", "Inugami", "Korone", 
				"Av Homa Fetouaki Imm 69 Appt 9 Hay Ennahda Salé", "0607529415", LocalDate.now());
		Etudiant etud = new Etudiant(olduser);
		Professeur prof = new Professeur(olduser);
		List<Utilisateur> listuser = new ArrayList<Utilisateur>();
		listuser.add(olduser);
		Mockito.when(mockedstudentrepository.save(etud)).thenReturn(etud);
		Mockito.when(mockedprofessorrepository.save(prof)).thenReturn(prof);
		Mockito.when(mockeduserrepository.save(olduser)).thenReturn(olduser);
		Mockito.when(mockeduserrepository.findByEmail(olduser.getEmail())).thenReturn(listuser);
		assertEquals("Already exists.",usercontroller.signupnoimage(olduser));
		Mockito.verify(mockeduserrepository, Mockito.times(1)).findByEmail(ArgumentMatchers.any(String.class));
		Mockito.verify(mockedstudentrepository, Mockito.times(0)).save(ArgumentMatchers.any(Etudiant.class));
		Mockito.verify(mockedprofessorrepository, Mockito.times(0)).save(ArgumentMatchers.any(Professeur.class));
	}
	
	
	
	
	@Test
	@DisplayName("Test Should pass when new user sign up!")
	public void addnewusertest() throws IOException {
		
		UserController usercontroller = new UserController(mockedstudentrepository,mockedprofessorrepository,mockeduserrepository);
		//koroneinugami@student.emi.ac.ma
		//ahmedChoukri@gmail.com
		//"Already exists."
		Utilisateur newuser = new Utilisateur( 100L,"koroneinugami@student.emi.ac.ma", "koronesuki", "ROLE_ETUDIANT", "Inugami", "Korone", 
				"Av Homa Fetouaki Imm 69 Appt 9 Hay Ennahda Salé", "0607529415", LocalDate.now());
		Etudiant etud = new Etudiant(newuser);
		Professeur prof = new Professeur(newuser);
		List<Utilisateur> listvide = Collections.emptyList();;
		Mockito.when(mockedstudentrepository.save(etud)).thenReturn(etud);
		Mockito.when(mockedprofessorrepository.save(prof)).thenReturn(prof);
		Mockito.when(mockeduserrepository.save(newuser)).thenReturn(newuser);
		Mockito.when(mockeduserrepository.findByEmail(newuser.getEmail())).thenReturn(listvide);
		assertEquals("User is added successfully.",usercontroller.signupnoimage(newuser));
		Mockito.verify(mockedstudentrepository, Mockito.times(1)).save(ArgumentMatchers.any(Etudiant.class));
		Mockito.verify(mockedprofessorrepository, Mockito.times(0)).save(ArgumentMatchers.any(Professeur.class));
	}
	
	
	
	
	@Test
	@DisplayName("Test Should pass when user exists in DB!")
	public void userexists() {
		LocalDate ldtParsed= LocalDate.parse("2020-11-08");
		Long iduser = (long) 6;
		String emailuser = "ahmedChoukri@gmail.com";
		Etudiant expectedetud = new Etudiant(iduser, "ahmedChoukri@gmail.com", "$2a$10$j2aA1o.UtDp1HVvbcsDwKecI/fLsEIgZyDew5YsJ4vD.15pcb2gbG", "ROLE_ETUDIANT",
				"Choukri", "Anwar", "Hay El Houda Rue 05 NR 40", "063265897", ldtParsed);
		Mockito.when(mockedstudentrepository.findById(iduser)).thenReturn(Optional.of(expectedetud));
		Mockito.when(mockedusercontroller.getStudent("ahmedChoukri@gmail.com")).thenReturn(expectedetud);
		
		Etudiant etud = mockedusercontroller.getStudent(emailuser);
		assertNotNull(mockedstudentrepository.findById(iduser));
		assertNotNull(etud);
        assertEquals(etud.getPassword(), expectedetud.getPassword());
	}
	
	

	
	

	@Test
    @DisplayName("Test Should Pass When password is conform to passwords rules using Junit")
	public void passwordshouldbevalid() {
        UserController userController = new UserController();
        assertTrue(userController.pwdisValid("MohammedBensaid.99"));
        assertThat(userController.pwdisValid("bensaid")).isFalse();      
    }
	
	@Test
    @DisplayName("Test Should Pass When email is valid using AssertJ")
    public void emailshouldbevalid() {
        UserController userController = new UserController();
        assertThatThrownBy(() -> {
        	userController.isValid("");
        	//On fournit un email null.
        }).isInstanceOf(Exception.class)
                .hasMessage("Email should not be empty! Please insert a valid mail and try again.");
    }

}

































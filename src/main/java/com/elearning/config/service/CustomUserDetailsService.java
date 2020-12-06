package com.elearning.config.service;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearning.entities.Utilisateur;
import com.elearning.repositories.UtilisateurRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UtilisateurRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user= userRepository.findByEmail(username).get(0);
		
		if(user==null) {
			throw new UsernameNotFoundException("Invalid email or password !");
		}
		return new User(user.getEmail(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
	}
	
}

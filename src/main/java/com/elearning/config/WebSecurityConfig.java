package com.elearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public void  configueGlobl(final AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("GET", "PUT", "POST",
		"DELETE").allowedHeaders("*");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/users/update/**").hasAnyAuthority("ROLE_ETUDIANT","ROLE_PROFESSEUR")
		.antMatchers("/users/**").permitAll()
		.antMatchers("/images/delete/**").hasAnyAuthority("ROLE_PROFESSEUR","ROLE_ETUDIANT")
		.antMatchers("/images/**").permitAll()
		.antMatchers("/courses/add")
		.hasAuthority("ROLE_PROFESSEUR")
		.antMatchers("/courses/update/**")
		.hasAuthority("ROLE_PROFESSEUR")
		.antMatchers("/courses/delete/**")
		.hasAuthority("ROLE_PROFESSEUR")
		.antMatchers("/courses/subscribe/**","/courses/unsubscribe/**")
		.hasAuthority("ROLE_ETUDIANT")
		.antMatchers("/courses/**").permitAll()
		.anyRequest().authenticated();
		http
		.httpBasic()
		.and()
		.cors()
		.and()
		.csrf()
		.disable()
		.headers()
		.frameOptions()
		.deny();
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

package com.gl.studentMgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.studentMgmt.service.UserDetailServiceImpl;

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public PasswordEncoder getBcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getUserDetailsService());
		auth.setPasswordEncoder(getBcryptPasswordEncoder());
		return auth;
	}

	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) {
		authBuilder.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/books/list", "/books/showFormForAdd", "/books/save", "/")
				.hasAnyAuthority("USER", "ADMIN").antMatchers("/books/showFormForUpdate", "/books/delete")
				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.successForwardUrl("/books/list").and().logout().logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/books/403").and().csrf().and().cors().disable();
	}

}

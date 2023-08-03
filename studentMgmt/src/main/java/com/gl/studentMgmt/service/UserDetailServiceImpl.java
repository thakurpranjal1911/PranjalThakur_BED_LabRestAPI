package com.gl.studentMgmt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.studentMgmt.model.User;
import com.gl.studentMgmt.repository.UserRepository;
import com.gl.studentMgmt.security.MyUserDetails;



public class UserDetailServiceImpl implements UserDetailsService {
	
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException(username+" is not present");
		}
		MyUserDetails userDetails = new MyUserDetails(user);
		return userDetails;
	}

}

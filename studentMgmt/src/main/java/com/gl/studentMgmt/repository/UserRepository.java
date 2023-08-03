package com.gl.studentMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.studentMgmt.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.user_name = ?1")
	public User findByName(String name);

}

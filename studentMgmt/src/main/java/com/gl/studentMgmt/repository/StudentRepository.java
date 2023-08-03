package com.gl.studentMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.studentMgmt.model.Student;

public interface StudentRepository extends JpaRepository<Student,String> {

}

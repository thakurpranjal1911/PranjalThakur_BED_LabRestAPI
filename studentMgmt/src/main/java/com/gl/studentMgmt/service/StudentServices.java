package com.gl.studentMgmt.service;

import java.util.List;
import java.util.Optional;

import com.gl.studentMgmt.model.Student;

public interface StudentServices {

	// Insert and Update
	public void save(Student student);

	// Delete
	public void delete(String student_id);

	// Print All Records
	public List<Student> getStudents();
	
	//Find Student
	public Student findStudentById(String student_id);

}

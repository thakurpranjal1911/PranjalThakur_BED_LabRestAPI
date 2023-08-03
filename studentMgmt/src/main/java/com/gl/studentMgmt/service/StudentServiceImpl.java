package com.gl.studentMgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.studentMgmt.model.Student;
import com.gl.studentMgmt.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServices {
	
    @Autowired
	StudentRepository repository;

	@Override
	public void save(Student student) {

		repository.save(student);

	}

	@Override
	public void delete(String student_id) {

		repository.deleteById(student_id);

	}

	@Override
	public List<Student> getStudents() {
		return repository.findAll();
	}

	@Override
	public Student findStudentById(String student_id) {
		Optional<Student> optBook = repository.findById(student_id);
		if (optBook.isPresent()) {
			return optBook.get();
		} else {
			throw new RuntimeException("Book Id is not present");
		}
	}

}

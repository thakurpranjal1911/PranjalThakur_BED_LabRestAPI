package com.gl.studentMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.studentMgmt.model.Student;
import com.gl.studentMgmt.service.StudentServices;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentServices service;

	@RequestMapping("/list")
	public String viewListOfStudents(Model model) {
		List<Student> students = service.getStudents();
		model.addAttribute("students", students);
		return "students/student-list";
	}

	@GetMapping("/showFormForAdd")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "students/student-form";
	}

	@PostMapping("/showFormForUpdate")
	public String updateBook(Model model, @RequestParam("student_id") int student_id) {
		String id = String.valueOf(student_id);
		Student student = service.findStudentById(id);
		model.addAttribute("student", student);
		return "students/student-form";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("student_id") int student_id) {
		String id = String.valueOf(student_id);
		service.delete(id);
		return "redirect:/students/list";
	}

	@PostMapping("/save")
	public String save(@RequestParam("student") Student student) {
		service.save(student);
		return "redirect:/students/list";
	}

}

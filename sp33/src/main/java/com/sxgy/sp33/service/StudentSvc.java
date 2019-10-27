package com.sxgy.sp33.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sxgy.sp33.bean.Student;
import com.sxgy.sp33.repository.StudentRepository;

@Service
public class StudentSvc {
	@Resource
	private StudentRepository studentRepository;
	@Transactional
	public void saveAll(List<Student> students) {
		studentRepository.saveAll(students);
	}
	@Transactional
	public Student getStudentByName(String name) {
		return studentRepository.findByName(name);
	}
	@Transactional
	public List<Student> getStudentByNameAndAddress(String name,String address){
		return studentRepository.findByNameAndAddress(name, address);
	}
	@Transactional
	public List<Student> getStudentByNameLike(String name){
		return studentRepository.findByNameLike("%"+name+"%");
	}
}

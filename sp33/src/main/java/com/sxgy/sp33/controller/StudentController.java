package com.sxgy.sp33.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxgy.sp33.bean.Student;
import com.sxgy.sp33.service.StudentSvc;

@Controller
@ResponseBody
@RequestMapping("/student")
public class StudentController {
	@Resource
	private StudentSvc studentSvc;
	@RequestMapping("/save")
	public String save() {
		Student s1=new Student();
		s1.setName("孙悟空");
		s1.setAddress("广州");
		Student s2=new Student();
		s2.setName("蜘蛛精");
		s2.setAddress("广州");
		Student s3=new Student();
		s3.setName("牛魔王");
		s3.setAddress("广州");
		List<Student> list=new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		studentSvc.saveAll(list);
		return "保存数据成功";
	}
	@RequestMapping("/name")
	public Student getByName(String name) {
		return studentSvc.getStudentByName(name);
	}
	@RequestMapping("/address")
	public List<Student> getByNameAndAddress(String name,String address) {
		return studentSvc.getStudentByNameAndAddress(name, address);
	}
	@RequestMapping("/like")
	public List<Student> getByNameLike(String name) {
		return studentSvc.getStudentByNameLike(name);
	}
}

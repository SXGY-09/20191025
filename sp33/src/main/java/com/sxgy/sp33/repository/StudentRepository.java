package com.sxgy.sp33.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sxgy.sp33.bean.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	/**
	 * select * from tb where name=?
	 * @param name
	 * @return
	 */
	Student findByName(String name);
	/**
	 * select * from tb where name=? and address=?
	 * @param name
	 * @param address
	 * @return
	 */
	List<Student> findByNameAndAddress(String name,String address);
	/**
	 * select * from tb where name like ?
	 * @param name
	 * @return
	 */
	List<Student> findByNameLike(String name);
}

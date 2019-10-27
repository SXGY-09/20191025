package com.sxgy.sp33.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sxgy.sp33.bean.Stu;

public interface StuRepository extends JpaRepository<Stu, Integer> {
	/**
	 * select s.* from Stu s where s.clz.name=?1
	 * 
	 * @param clzName
	 * @return
	 */
	List<Stu> findByClz_name(String clzName);

	/**
	 * the same as above
	 * 
	 * @param clzName
	 * @return
	 */
	@Query("select s from Stu s where s.clz.name=?1")
	List<Stu> findStusByClzName(String clzName);

	/**
	 * 
	 * @param clzName
	 * @return
	 */
	@Query("select new Map(s.name as name,s.sex as sex) from Stu s where s.clz.name=?1")
	List<Map<String, Object>> findNameAndSexByClzName(String clzName);

	/**
	 * 
	 * @param clzName
	 * @param sex
	 * @return
	 */
	@Query("select s.name from Stu s where s.clz.name=:clzName and s.sex=:sex")
	List<String> findNameByClzNameAndSex(@Param("clzName") String clzName, @Param("sex") char sex);

	/**
	 * 
	 * @param stuName
	 * @return
	 */
	@Query("select c.name from Clz c inner join c.stu s where s.name=?1")
	String findClzNameByStuName(String stuName);

	/**
	 * 
	 * @param stuName
	 * @return
	 */
	@Modifying
	@Query("delete from Stu s where s.name=?1")
	int deleteStuByStuName(String stuName);
	/**
	 * QamedQuery自定义方法,一个名称映射一个查询语句
	 * @param clzName
	 * @return
	 */
	List<Stu> customFind(String clzName);
}

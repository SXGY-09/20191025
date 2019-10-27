package com.sxgy.sp33.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sxgy.sp33.bean.Clz;
import com.sxgy.sp33.bean.Stu;
import com.sxgy.sp33.repository.ClzRepository;
import com.sxgy.sp33.repository.StuRepository;

@Service
public class SchoolSvc {
	@Resource
	private StuRepository stuRepository;
	@Resource
	private ClzRepository clzRepository;

	@Transactional
	public void saveClzAll(List<Clz> clzs) {
		clzRepository.saveAll(clzs);
	}

	@Transactional
	public void saveStuAll(List<Stu> stus) {
		stuRepository.saveAll(stus);
	}

	public List<Map<String, Object>> getStusByClzName(String clzName) {
		List<Stu> stus = stuRepository.findByClz_name(clzName);
		// stus=stuRepository.findStusByClzName(clzName);//结果一致
		List<Map<String, Object>> results = new ArrayList<>();
		for (Stu stu : stus) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", stu.getName());
			map.put("age", stu.getAge());
			map.put("sex", stu.getSex());
			results.add(map);
		}
		return results;
	}

	public List<Map<String, Object>> findNameAndSexByClzzName(String clzName) {
		return stuRepository.findNameAndSexByClzName(clzName);
	}

	public List<String> findNameByClzNameAndSex(String clzName, char sex) {
		return stuRepository.findNameByClzNameAndSex(clzName, sex);
	}

	public String findClzNameByStuName(String stuName) {
		return stuRepository.findClzNameByStuName(stuName);
	}

	@Transactional
	public int deleteByStuName(String stuName) {
		return stuRepository.deleteStuByStuName(stuName);
	}
	public List<Stu> customFind(String clzName){
		return stuRepository.customFind(clzName);
	}
}

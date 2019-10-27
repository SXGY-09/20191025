package com.sxgy.sp33.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sxgy.sp33.bean.Clz;
import com.sxgy.sp33.bean.Stu;
import com.sxgy.sp33.repository.ClzRepositorySpecification;
import com.sxgy.sp33.repository.StuRepositorySpecification;

@Service
public class SpecificationSvc {
	@Resource
	private StuRepositorySpecification stuRepositorySpecification;
	@Resource
	private ClzRepositorySpecification clzRepositorySpecification;

	@Transactional
	public void saveClzAll(List<Clz> clzs) {
		clzRepositorySpecification.saveAll(clzs);
	}

	@Transactional
	public void saveStuAll(List<Stu> stus) {
		stuRepositorySpecification.saveAll(stus);
	}

	/**
	 * jpql:select s from Stu s where s.clz.sex=?1
	 * 
	 * @param sex
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Map<String, Object>> getStusBySex(char sex) {
		List<Stu> stus = stuRepositorySpecification.findAll(new Specification<Stu>() {
			@Override
			public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// root.get("sex")表示获取sex这个字段名称，equal表示执行equal查询
				Predicate p1 = criteriaBuilder.equal(root.get("sex"), sex);
				return p1;
			}
		});
		List<Map<String, Object>> result = new ArrayList<>();
		for (Stu stu : stus) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", stu.getName());
			map.put("age", stu.getAge());
			map.put("sex", stu.getSex());
			result.add(map);
		}
		return result;
	}

	/**
	 * query dynamic:模糊匹配姓名，地址
	 * 
	 * @param stu
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Map<String, Object>> getStusByDynamic(Stu stu) {
		List<Stu> stus = stuRepositorySpecification.findAll(new Specification<Stu>() {
			@Override
			public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 本集合用于封装查询条件
				List<Predicate> predicates = new ArrayList<>();
				if (stu != null) {
					/** 是否传入用于查询的姓名 */
					if (!StringUtils.isEmpty(stu.getName())) {
						predicates.add(cb.like(root.<String>get("name"), "%" + stu.getName() + "%"));
					}
					/** 是否传入用于查询的地址 */
					if (!StringUtils.isEmpty(stu.getAddress())) {
						predicates.add(cb.like(root.<String>get("address"), "%" + stu.getAddress() + "%"));
					}
					/** 是否传入用于查询的性别 */
					if (stu.getSex() != null) {
						predicates.add(cb.equal(root.<String>get("sex"), stu.getSex()));
					}
					/** 是否传入用于查询的班级信息 */
					if (stu.getClz() != null && !StringUtils.isEmpty(stu.getClz().getName())) {
						root.join("clz", JoinType.INNER);
						Path<String> clzName = root.get("clz").get("name");
						predicates.add(cb.equal(clzName, stu.getClz().getName()));
					}
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		});
		List<Map<String, Object>> result = new ArrayList<>();
		for (Stu stu1 : stus) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", stu1.getName());
			map.put("age", stu1.getAge());
			map.put("sex", stu1.getSex());
			map.put("address", stu1.getAddress());
			map.put("clzName", stu1.getClz().getName());
			result.add(map);
		}
		return result;
	}

	/**
	 * sort and paging
	 * 
	 * @param clzName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("serial")
	public Page<Stu> getStusByPage(String clzName, int pageIndex, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Page<Stu> pages = stuRepositorySpecification.findAll(new Specification<Stu>() {
			@Override
			public Predicate toPredicate(Root<Stu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				root.join("clz", JoinType.INNER);
				Path<String> cn = root.get("clz").get("name");
				Predicate p1 = criteriaBuilder.equal(cn, clzName);
				return p1;
			}
		}, PageRequest.of(pageIndex - 1, pageSize, sort));
		return pages;
	}
}

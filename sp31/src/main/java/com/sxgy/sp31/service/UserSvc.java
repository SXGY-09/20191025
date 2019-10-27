package com.sxgy.sp31.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sxgy.sp31.bean.User;
import com.sxgy.sp31.repository.UserRepository;

@Service
public class UserSvc {
	// 注入UserRepository
	@Resource
	private UserRepository userRepository;

	@Transactional // 绑定事务
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	@Transactional
	public User getById(Integer id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.get();
	}

	@Transactional
	public void update(User user) {
		// 直接调用持久化对象的set方法修改属性
		user.setUsername("孙悟空");
		user.setLoginName("skw");
	}
}

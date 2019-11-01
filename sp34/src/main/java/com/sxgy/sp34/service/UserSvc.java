package com.sxgy.sp34.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxgy.sp34.bean.User;
import com.sxgy.sp34.mapper.UserMapper;

@Service
public class UserSvc {
	@Resource
	private UserMapper userMapper;

	public int insert(User user) {
		return userMapper.insert(user);
	}

	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	public List<User> findAll() {
		return userMapper.findAll();
	}

	public void insertGetKey(User user) {
		userMapper.insertGetKey(user);
	}

	public void update(User user) {
		userMapper.update(user);
	}

	public void delete(Integer id) {
		userMapper.delete(id);
	}
}

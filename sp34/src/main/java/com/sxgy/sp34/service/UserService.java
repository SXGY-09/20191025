package com.sxgy.sp34.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxgy.sp34.bean.User;
import com.sxgy.sp34.repository.UserRepository;

@Service
public class UserService {
	@Resource
	private UserRepository userRepository;
	public int insertUser() {
		return userRepository.insertUser();
	}
	public User selectByUsername(String username) {
		return userRepository.selectByUsername(username);
	}
	public User selectById(Integer id) {
		return userRepository.selectById(id);
	}
	public List<User> findAll(){
		return userRepository.findAll();
	}
	public User insertGetKey(User user) {
		return userRepository.insertGetKey(user);
	}
	public void update(User user) {
		userRepository.update(user);
	}
	public void delete(int id) {
		userRepository.delete(id);
	}
}

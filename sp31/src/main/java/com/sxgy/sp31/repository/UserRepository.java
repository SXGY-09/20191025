package com.sxgy.sp31.repository;

import org.springframework.data.repository.CrudRepository;

import com.sxgy.sp31.bean.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}

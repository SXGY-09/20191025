package com.sxgy.jpasecurity.repository;

import com.sxgy.jpasecurity.pojo.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SXGY_09
 * @description
 * @timestamp 2019-11-02 17:00
 */
public interface UserRepository extends JpaRepository<MyUser,Long> {
    MyUser findByLoginName(String loginName);
}

package com.sxgy.jpasecurity.service;

import com.sxgy.jpasecurity.pojo.MyRole;
import com.sxgy.jpasecurity.pojo.MyUser;
import com.sxgy.jpasecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SXGY_09
 * @description
 * @timestamp 2019-11-02 17:02
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //实际上通过loginName查询
        MyUser myUser=userRepository.findByLoginName(username);
        if(myUser==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorityList=new ArrayList<>();
        List<MyRole> roles=myUser.getRoles();
        for(MyRole role:roles){
            authorityList.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        //返回org.springframework.security.core.userdetails.User
        return new User(myUser.getUsername(),myUser.getPassword(),authorityList);
    }
}

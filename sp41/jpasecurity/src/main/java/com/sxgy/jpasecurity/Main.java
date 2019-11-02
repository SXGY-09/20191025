package com.sxgy.jpasecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author SXGY_09
 * @description
 * @timestamp 2019-11-02 17:41
 */
public class Main {
    public static void main(String[] args) {
        String admin=new BCryptPasswordEncoder().encode("admin");
        String user=new BCryptPasswordEncoder().encode("user");
        System.out.println(admin);
        System.out.println(user);

    }
}

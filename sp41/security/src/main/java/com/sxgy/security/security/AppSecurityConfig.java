package com.sxgy.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    //用户授权操作
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("AppSecurityConfig config()......");
        http.authorizeRequests()
                .antMatchers("/login","/css/**","/js/**","/img/*").permitAll()
                .antMatchers("/","/home").hasRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(appAuthenticationSuccessHandler)
                //分别对应表单元素的name属性
                .usernameParameter("loginName")
                .passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
    //用户认证操作
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("AppSecurityConfig configureGlobal()......");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("ydm").password("123").roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("admin").password("123").roles("ADMIN","DBA");
    }
}

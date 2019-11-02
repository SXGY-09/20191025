package com.sxgy.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SXGY_09
 * @description 控制器
 * @date 2019-11-02 11:29
 *
 */
@Controller
public class AppController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("user",this.getUsername());
        model.addAttribute("role",this.getAuthority());
        return "home";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String adminPage(Model model){
        model.addAttribute("user",this.getUsername());
        model.addAttribute("role",this.getAuthority());
        return "admin";
    }
    @RequestMapping("/dba")
    public String toDba(Model model){
        model.addAttribute("user",this.getUsername());
        model.addAttribute("role",this.getAuthority());
        return "dba";
    }
    @RequestMapping("/accessDenied")
    public String accessDeniedPage(Model model){
        model.addAttribute("user",this.getUsername());
        model.addAttribute("role",this.getAuthority());
        return "accessDenied";
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        //如果认证信息不空，注销
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/login?logout";
    }

    /**
     * 获取当前用户名称
     * @return
     */
    private String getUsername() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username="+username);
        return username;
    }

    /**
     * 获取当前用户权限
     * @return
     */
    private String getAuthority(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        List<String> roles=new ArrayList<>();
        for(GrantedAuthority authority:authentication.getAuthorities()){
            roles.add(authority.getAuthority());
        }
        System.out.println("roles="+roles);
        return roles.toString();
    }
}

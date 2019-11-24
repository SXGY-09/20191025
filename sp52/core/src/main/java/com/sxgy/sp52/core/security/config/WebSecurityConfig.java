package com.sxgy.sp52.core.security.config;

import com.sxgy.sp52.core.security.dto.Constances;
import com.sxgy.sp52.core.security.dto.CustomAuthenticationEntryPoint;
import com.sxgy.sp52.core.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 * @author SXGY_09
 * @description security配置
 * @date 2019-11-15 21:17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(Constances.AUTH_WHITELIST);
    }

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .access("@authService.canAccess(request,authentication)")
                .and()
                //异常处理,可以再此使用entryPoint来定义错误输出
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
//不需要session来控制,所以这里可以去掉
//                .and()
//                .securityContext()
//                .securityContextRepository(new NullSecurityContextRepository())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //开启匿名访问
                .and()
                .anonymous()
                //退出登录自己来控制
                .and()
                .logout().disable()
                //因为没用到cookies,所以关闭cookies,防止循环定向
                .csrf().disable()
                //允许跨域
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);

    }
}

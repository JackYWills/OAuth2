package com.sofaseal.authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @Description:
 * @Author: JackYan
 * @Date2019/12/24 11:43
 * @Version V1.0
 **/
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .roles("USER","ADMIN")
                .and()
                .withUser("jack")
                .password("123456")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf保护功能（跨域访问）
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();//访问API下无需登录认证权限
    }
}

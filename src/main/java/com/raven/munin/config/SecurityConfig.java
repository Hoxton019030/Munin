package com.raven.munin.config;

import com.raven.munin.enumeration.MemberAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
                .antMatchers(HttpMethod.GET,"/h2/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin();
        //讓spring Security可以和h2建立連線
        http.headers().frameOptions().disable();
    }

    /**
     *
     * @param auth 配置全局驗證資訊，如Authentication Provider,UserDetailService
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());


    }
}

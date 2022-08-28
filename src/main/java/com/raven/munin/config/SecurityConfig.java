package com.raven.munin.config;

import com.raven.munin.enumeration.MemberAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    private    UserDetailsService userDetailsService;

    /**
     * 協助帳號密碼認證的東西
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(){
        try {
            return super.authenticationManagerBean();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
                .antMatchers(HttpMethod.GET,"/h2/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
///               .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin();
        //讓spring Security可以和h2建立連線
        http.headers().frameOptions().disable();
    }

    /**
     *
     * @param auth 配置全局驗證資訊，如Authentication Provider,UserDetailService等等資訊，
     *             authenticationManager會接收到UsernamePasswordAuthenticationToken傳入的資料後
     *             調用SecurityConfig中所配置的userDetailsService,passwordEncoder來協助驗證
     *
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

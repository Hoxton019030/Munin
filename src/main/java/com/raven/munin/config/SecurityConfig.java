package com.raven.munin.config;

import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private   UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 協助帳號密碼認證的東西
     * @return
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();

    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
//                .antMatchers(HttpMethod.GET,"/h2/**").hasAuthority(MemberAuthority.SYSTEM_ADMIN.name())
                .antMatchers(HttpMethod.GET,"/login/**").permitAll()
//                .antMatchers(HttpMethod.POST,"login").permitAll()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().permitAll()
                .and()
                // FIXME: 2022/8/28 因循環依賴filter>service>config>filter的關係，這邊是直接創造物件，不確定會不會出問題
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable();
//                .formLogin();
        http.headers().frameOptions().disable();
        //讓spring Security可以和h2建立連線
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

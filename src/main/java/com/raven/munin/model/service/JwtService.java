package com.raven.munin.model.service;

import com.raven.munin.auth.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;

@Service
public class JwtService {

    private final String KEY = "memory";

    @Autowired
    private AuthenticationManager authenticationManager;

    public String generateToken(AuthRequest request){
        Authentication usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        //AuthenticationManager支持多種身分驗證方式，執行時會接收Authentication介面的物件。若是以帳號密碼的方式來驗證，
        //則應使用UsernamePasswordAuthenticationToken，其建構子的第一個及第二個分別是principal及credentials
        //principal: adj主要的，代表與伺服器互動的人
        //credentials: n 證書，用來證明自己是principal的資料
        usernamePasswordAuthenticationToken = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //authenticationManager接收到UsernamePasswordAuthenticationToken後，在底層會透過SecurityConfig的中所配置的
        // UserDetailsService與PasswordEncoder來協助驗證，若帳密有誤則會回傳Http:403
        UserDetails userDetails = (UserDetails) usernamePasswordAuthenticationToken.getPrincipal();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,2);
        Claims claims = Jwts.claims();
        claims.put("username",userDetails.getUsername());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("Programming Classroom");
        SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes());



        return  Jwts.builder().
                setClaims(claims).
                signWith(secretKey)
                .compact();
    }




}

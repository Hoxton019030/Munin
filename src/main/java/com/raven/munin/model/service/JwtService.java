package com.raven.munin.model.service;

import com.raven.munin.auth.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
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

    private final String KEY = "HuginAndMunin ";

    @Autowired
    private AuthenticationManager authenticationManager;

    public String generateToken(AuthRequest request) {
        Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        // AuthenticationManager支持多種身分驗證方式，執行時會接收Authentication介面的物件。若是以帳號密碼的方式來驗證，
        // 則應使用UsernamePasswordAuthenticationToken，其建構子的第一個及第二個分別是principal及credentials
        // principal: adj主要的，代表與伺服器互動的人
        // credentials: n 證書，用來證明自己是principal的資料
        usernamePasswordAuthenticationToken = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // authenticationManager接收到UsernamePasswordAuthenticationToken後，在底層會透過SecurityConfig的中所配置的
        // UserDetailsService與PasswordEncoder來協助驗證，若帳密有誤則會回傳Http:403
        UserDetails userDetails = (UserDetails) usernamePasswordAuthenticationToken.getPrincipal();
        // 雖然這邊返回的都是Authentication的物件，但principal的資料會變成UserDetailsService的回傳值，也就是UserDetails

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);


        {
            Claims claims = Jwts.claims();  //宣告一個Claims的物件，用來放置JwToken的內容
            claims.put("username", userDetails.getUsername()); //設置使用者帳號
            claims.setExpiration(calendar.getTime()); //設置到期時間
            claims.setIssuer("Programming Classroom"); //設置核發者
            SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes());


            return Jwts.builder().setClaims(claims).signWith(secretKey).compact();
        }


    }
}

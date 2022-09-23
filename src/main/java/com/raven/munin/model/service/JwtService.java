package com.raven.munin.model.service;

import com.raven.munin.auth.AuthRequest;
import com.raven.munin.properties.JwTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private JwTokenProperties jwTokenProperties;
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
        calendar.add(Calendar.MINUTE, jwTokenProperties.getEXPIRATION_TIME());


        {
            Claims claims = Jwts.claims();  //宣告一個Claims的物件，用來放置JwToken的內容
            claims.put("username", userDetails.getUsername()); //設置使用者帳號
            claims.setExpiration(calendar.getTime()); //設置到期時間
            claims.setIssuer("Programming Classroom"); //設置核發者
            SecretKey secretKey = Keys.hmacShaKeyFor(jwTokenProperties.getKEY().getBytes());


            return Jwts.builder().setClaims(claims).signWith(secretKey).compact();
        }


    }
    public Map<String,Object> parseToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(jwTokenProperties.getKEY().getBytes());
        //準備好一密鑰
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        //建立解析器，建立簽名
        Claims claims = parser.parseClaimsJws(token).getBody();
        //透過解析器的parseClaimJwt方法，可以解析含有簽名的JWT，再呼叫getBody方法，取得claims(所有權)物件，
        //若JWT的內容有存生肖期間或到期期間，在解析時，parser會去自動判斷這個token是否在有效期間內，若不在
        //期間內則會拋出io.jsonwebtoken.ExpiredJwtException的例外
        return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }
}

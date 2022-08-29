package com.raven.munin.filter;

import com.raven.munin.model.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    //注入JwtService UserDetailsService，分別用來解析Token與查詢使用者詳情


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            String accessToken = authHeader.replace("Bearer ", "");
            //從請求標頭中取得Authorization欄位中的值
            Map<String, Object> claims = jwtService.parseToken(accessToken);
            //擷取出後面的JWT字串，接著解析它
            String username = (String) claims.get("username");
            //從claims物件中取得username欄位的值
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //並透過userDetailService查詢使用者詳情。這也代表JWT的內容(payload)必須包含username這個欄位
            //在filter中查詢使用者的目的，是為了將該次請求所代表的驗證後資料(Authentication)帶進security中的Context。
            //Context是一種較抽象的概念，可以想像成該次請求的身分狀態

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //為了將這個請求的使用者身分告訴伺服器，我們建立UsernamePasswordAuthenticationToken的物件，其中第三個參數放GrantedAuthority的List，作為API的授權檢查
            //第一個參數(principal)傳入使用者詳請(UserDetails)。
            // 而第二個參數是credential，通常是密碼，但不傳入也無訪
            //經由傳入principal，我們得以在業務邏輯中從Context輕易獲取使用者身分的資料
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);


    }
}

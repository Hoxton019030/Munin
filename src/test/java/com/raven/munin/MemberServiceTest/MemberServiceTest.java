package com.raven.munin.MemberServiceTest;

import com.raven.munin.auth.AuthRequest;
import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.model.request.member.MemberReq;
import com.raven.munin.model.service.JwtService;
import com.raven.munin.model.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void createMember(){
        MemberReq newMember = MemberReq.builder().id("S05").
                password("123").
                name("Hoxton").
                createTime(LocalDateTime.now()).
                updateTime(LocalDateTime.now()).
        authority(MemberAuthority.BOARD_MANAGER).build();
        System.out.println(newMember);

        memberService.createMember(newMember);

    }
    @Test
    public void print(){
        System.out.println(MemberAuthority.BANNED_MEMBER);
    }
    @Test
    public void getKey(){
        System.out.println("\"Memory\".getBytes() = " + Arrays.toString("Memory".getBytes()));
    }
    @Test
    public void generateToken(){
        JwtService jwtService = new JwtService();
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("S01");
        authRequest.setPassword("123");
        String jwToken = jwtService.generateToken(authRequest);
        System.out.println("jwToken = " + jwToken);
    }

}

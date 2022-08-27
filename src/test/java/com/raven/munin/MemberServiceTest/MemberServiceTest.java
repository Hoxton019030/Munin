package com.raven.munin.MemberServiceTest;

import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.model.request.member.MemberReq;
import com.raven.munin.model.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void createMember(){
        MemberReq newMember = MemberReq.builder().id("S06").
                code("123").
                name("Hoxton").
                createTime(LocalDateTime.now()).
                updateTime(LocalDateTime.now()).
        authority(MemberAuthority.getEnum("SYSTEM_ADMIN")).build();
        System.out.println(newMember);


        memberService.createMember(newMember);

    }
    @Test
    public void print(){
        System.out.println(MemberAuthority.BANNED_MEMBER);
    }

}

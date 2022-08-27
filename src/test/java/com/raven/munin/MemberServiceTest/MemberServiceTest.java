package com.raven.munin.MemberServiceTest;

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
        MemberReq newMember = MemberReq.builder().id("S02").
                code("123").
                name("Hoxton").
                createTime(LocalDateTime.now()).
                updateTime(LocalDateTime.now()).build();


        memberService.createMember(newMember);

    }

}

package com.raven.munin.model.service;

import com.raven.munin.model.entity.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hoxton
 * @version 1.1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void findMemberById() {
        Member s03 = memberService.findMemberById("S05");
        System.out.println(s03);

    }

    @Test
    void createMember() {
    }
}
package com.raven.munin.controller;

import com.raven.munin.model.entity.Member;
import com.raven.munin.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "{id}")
    public Member findMemberById(@PathVariable("id")String id){
        return memberService.findMemberById(id);
    }
    //
}

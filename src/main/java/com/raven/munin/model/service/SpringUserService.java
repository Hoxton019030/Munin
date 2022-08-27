package com.raven.munin.model.service;

import com.raven.munin.model.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 2022/8/27 應自訂一個錯誤類別是找不到使用者的部分
        Member member = memberService.findMemberById(username);
        return new User(member.getId(), member.getCode(), Collections.emptyList());
    }
}

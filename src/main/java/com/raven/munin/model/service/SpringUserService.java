package com.raven.munin.model.service;

import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.model.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<MemberAuthority> memberAuthority = new ArrayList<>();
        System.out.println("eeeeeeeeeeeeeeee");
        Member member = memberService.findMemberById(username);
        memberAuthority.add(member.getAuthority());
        List<SimpleGrantedAuthority> authorities = memberAuthority.stream().map(auth -> new SimpleGrantedAuthority(auth.name())).collect(Collectors.toList());
        return new User(member.getId(), member.getPassword(), authorities);
    }
}

package com.raven.munin.config;

import com.raven.munin.enumeration.MemberAuthority;
import com.raven.munin.model.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 一個練習，練習如何自定義複雜一點的身分驗證
 */
public class SpringUser implements UserDetails {

    @Autowired
    Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<MemberAuthority> authority = new ArrayList<>();
        authority.add(member.getAuthority());
        return authority.stream().map(auth -> new SimpleGrantedAuthority(auth.name())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member.getCode();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

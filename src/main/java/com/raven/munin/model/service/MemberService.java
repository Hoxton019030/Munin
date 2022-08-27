package com.raven.munin.model.service;

import com.raven.munin.model.dao.MemberDao;
import com.raven.munin.model.entity.Member;
import com.raven.munin.model.request.member.MemberReq;
import com.raven.munin.model.response.member.MemberRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public Member findMemberById(String id){
        return memberDao.findMemberById(id).orElseThrow();
    }

    public MemberRes createMember(MemberReq memberReq){
        memberDao.findMemberById()

    }




}

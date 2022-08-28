package com.raven.munin.model.service;

import com.raven.munin.exception.MemberException;
import com.raven.munin.model.dao.MemberDao;
import com.raven.munin.model.entity.Member;
import com.raven.munin.model.request.member.MemberReq;
import com.raven.munin.model.response.member.MemberRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDao memberDao;
    private BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
    public Member findMemberById(String id){
        return memberDao.findMemberById(id).orElseThrow(() -> new MemberException("無此使用者"));
    }

    public MemberRes createMember(MemberReq memberReq){
        Optional<Member> member = memberDao.findMemberById(memberReq.getId());
        if(member.isEmpty()){
            Member newMember = new Member(
                    memberReq.getId(),
                    memberReq.getName(),
                    passwordEncoder.encode(memberReq.getPassword()) ,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    memberReq.getAuthority());
            memberDao.save(newMember);
            return MemberRes.builder().member(newMember).build();
        }
        else {
            throw new MemberException("該用戶已存在");
        }
    }
}

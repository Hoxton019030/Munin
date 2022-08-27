package com.raven.munin.model.service;

import com.raven.munin.model.dao.MemberDao;
import com.raven.munin.model.entity.Member;
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
        return memberDao.findMemberById(id);
    }


}

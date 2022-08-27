package com.raven.munin.model.dao;

import com.raven.munin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {

}

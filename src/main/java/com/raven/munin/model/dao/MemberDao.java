package com.raven.munin.model.dao;

import com.raven.munin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {

    @Query(value = "SELECT * FROM MEMBER WHERE id =:id",nativeQuery = true)
    public Member findMemberById(@Param("id") String id);



}

package com.raven.munin.model.dao;

import com.raven.munin.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {

    @Query(value = "SELECT * FROM MEMBER WHERE id =:id",nativeQuery = true)
    public Optional<Member> findMemberById(@Param("id") String id);



}

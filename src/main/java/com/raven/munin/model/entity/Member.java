package com.raven.munin.model.entity;

import com.raven.munin.enumeration.MemberAuthority;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", columnDefinition = "int", nullable = false)
    private Integer seq;

    /**
     * Encrypto: identity Card number
     */
    @Column(name = "ID", columnDefinition = "CHARACTER VARYING(150)", nullable = false, length = 150)
    private String id;

    /**
     * virtual avatar name
     */
    @Column(name = "NAME", columnDefinition = "CHARACTER VARYING(200)", nullable = false)
    private String name;

    /**
     * password(hashcode)
     */
    @Column(name = "CODE", columnDefinition = "CHARACTER VARYING(500)", nullable = false)
    private String code;

    /**
     * create time
     */
    @Column(name = "CREATE_TIME", columnDefinition = "TIMESTAMP default FORMATDATETIME(LOCALTIMESTAMP(), 'yyyy-MM-dd HH:mm:ss')", nullable = false)
    private LocalDateTime createTime;


    /**
     * update time
     */
    @Column(name = "UPDATE_TIME", columnDefinition = "TIMESTAMP", nullable = true)
    private LocalDateTime updateTime;

    @Column(name = "AUTHORITY", columnDefinition = "VARCHAR(500)", nullable = false)
    private MemberAuthority authority;

    public Member(String id, String name, String code, LocalDateTime createTime, LocalDateTime updateTime, MemberAuthority authority) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.authority = authority;
    }

    public Member() {
    }
}




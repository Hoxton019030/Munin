package com.raven.munin.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class    Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    /**
     * Article's title
     */
    @Column(name = "TITLE", columnDefinition = "CHARACTER VARYING(150)", nullable = false, length = 50)
    private String title;

    /**
     * Article's author name
     */
    @Column(name = "NAME", columnDefinition = "CHARACTER VARYING(200)")
    private String author;

    /**
     * Article's Likes
     */
    @Column(name = "LIKES", columnDefinition = "INTEGER", nullable = false)
    private Integer likes;

    /**
     * Article's content
     */
    @Column(name = "CONTENT",columnDefinition = "CLOB",nullable = false)
    private String content;

    /**
     * create time
     */
    @Column(name = "CREATE_TIME", columnDefinition = "TIMESTAMP default FORMATDATETIME(LOCALTIMESTAMP(),'yyyy-MM-dd HH:mm:ss')", nullable = false)
    private LocalDateTime createTime;

    /**
     * update time
     */
    @Column(name = "UPDATE_TIME", columnDefinition = "TIMESTAMP", nullable = true)
    private LocalDateTime updateTime;
}
package com.raven.munin.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false, columnDefinition = "INTEGER")
    private Integer SEQ;

    /**
     * Article's title
     */
    @Column(name = "TITLE", columnDefinition = "CHARACTER VARYING(150)", nullable = false, length = 50)
    private String title;

    /**
     * Article's author name
     */
    @Column(name = "AUTHOR", columnDefinition = "CHARACTER VARYING(200)")
    private String author;

    /**
     * Article's Likes
     */
    @Column(name = "LIKES", columnDefinition = "INTEGER", nullable = false)
    private Integer likes;

    /**
     * Article's content
     */
    @Column(name = "CONTENT", columnDefinition = "CLOB", nullable = false)
    private String content;

    /**
     * 控制文章是否可被一般用戶所看見，1為可以，2為不行
     */
    @Column(name = "VISIBLE", columnDefinition = "int", nullable = false)
    private int visible;

    @Column(name = "VIEWS", columnDefinition = "int", nullable = false)
    private int views;


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
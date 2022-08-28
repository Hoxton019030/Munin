package com.raven.munin.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    @Column(name="ARTICLE_ID",nullable = false, columnDefinition = "INTEGER")
    private Integer articleId;

    @Column(name="COMMENT_CONTENT",nullable = false,columnDefinition = "CHARACTER VARYING(150)")
    private String commentContent;

    @Column(name="VISIBLE",columnDefinition = "int",nullable = false)
    private int visible;
}
package com.raven.munin.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name="ARTICLEID",nullable = false, columnDefinition = "INTEGER")
    private Integer articleId;

    @Column(name="commentContent",nullable = false,columnDefinition = "CHARACTER VARYING(150)")
    private String commentContent;

    @Column(name="VISIBLE",columnDefinition = "int",nullable = false)
    private int visible;
}
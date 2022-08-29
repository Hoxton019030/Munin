package com.raven.munin.practice;

import lombok.Data;

@Data
public class Issue {
    private Long id;
    private ActiveState activeState;
    private ResultState resultState;
    private String creator;
    private String solver;
    private String log;
}

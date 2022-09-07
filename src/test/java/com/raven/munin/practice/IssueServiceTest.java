package com.raven.munin.practice;

import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class IssueServiceTest {
    public static final Long ID =1L;

    private IssueService issueService;

    @Before
    public void setUp(){
        issueService=new IssueService();
    }

    @Test(expected = EntityNotFoundException.class)
    public void queryById_shouldThrowException() {
        Throwable throwable = catchThrowable(() -> issueService.queryById(ID));
        assertThat(throwable).isExactlyInstanceOf(EntityNotFoundException.class).hasMessage("找步道實體");

        issueService.queryById(ID);
    }
}
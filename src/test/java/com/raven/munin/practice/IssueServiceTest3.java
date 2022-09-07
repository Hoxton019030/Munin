package com.raven.munin.practice;

import org.assertj.core.api.HamcrestCondition;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityNotFoundException;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;


public class IssueServiceTest3 {
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    private static final  Long ID = 1L;
    private IssueService issueService;

    @Before
    public void setUp(){
        issueService=new IssueService();
    }

    @Test
    public void queryById_ShouldThrowException(){
        thrown.expect(EntityNotFoundException.class);
        issueService.queryById(ID);
    }

    @Test
    public void queryById2_ShouldThrowException(){
        thrown.expect(hasProperty("ID",is(ID)));
        issueService.queryById(ID);

    }
}

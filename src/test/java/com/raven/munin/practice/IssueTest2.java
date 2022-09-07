package com.raven.munin.practice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssueTest2 {
    private static final String CREATOR = "CREATOR";

    @Test
    public void testIssue1isResolved() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator(CREATOR);
        IssueAssert.assertThatIssue(issue).isResolved();
    }
    @Test
    public void testIssue2isResolved(){
        Issue issue = new Issue();
        issue.setId(2L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator(CREATOR);
        issue.setLog("everything is ok.");
        IssueAssert.assertThatIssue(issue).isResolved().isResolved().isResolved();
    }

}

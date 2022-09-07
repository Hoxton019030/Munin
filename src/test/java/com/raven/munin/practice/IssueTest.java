package com.raven.munin.practice;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

/**
 * 錯誤示範
 * 1. 寫了兩個斷言，不易判斷
 * 2. 包含實現細節
 * 3. 把驗證邏輯寫在裡面，若日後驗證邏輯改變，會造成不易維護
 */
public class IssueTest {
    private static final String CREATOR ="CREATOR";

    @Test
    public void testIssue1isResolved(){
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator(CREATOR);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(issue.getActiveState()).
                overridingErrorMessage("已解決的問題活動狀態必須是(DONE)，但是為%s",issue.getActiveState())
        //overridingErrorMessage()方法表示驗證發生錯誤時，顯示我們自己編寫的錯誤訊息。
                .isEqualTo(ActiveState.DONE);
        softAssertions.assertThat(issue.getLog()).
                overridingErrorMessage("已解決的問題必須要有問題紀錄，但他的問題紀錄為 :%s",issue.getLog())
                .isNotNull();
        softAssertions.assertAll();

    }
}

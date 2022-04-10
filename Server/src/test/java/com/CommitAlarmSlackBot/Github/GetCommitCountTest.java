package com.CommitAlarmSlackBot.Github;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GetCommitCountTest {
    @Test
    void getcommitted() {
        GetCommitCount getCommitCount = new GetCommitCount();

        try {
            Assertions.assertThat(getCommitCount.committed(1)).isTrue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
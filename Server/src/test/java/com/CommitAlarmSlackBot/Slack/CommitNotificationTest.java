package com.CommitAlarmSlackBot.Slack;

import com.slack.api.methods.SlackApiException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommitNotificationTest {
    @Test
    void slackmessageTest() {
        CommitNotification commitNotification = new CommitNotification();
        try {
            commitNotification.notification();
        } catch (SlackApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
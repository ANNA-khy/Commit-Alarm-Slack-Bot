package com.CommitAlarmSlackBot.Slack;

import com.CommitAlarmSlackBot.Github.GetCommitCount;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public class CommitNotification {

    private final int minCommit = 1;
    private final String channelId;

    @Scheduled(cron = "0 0 2 0 * * ?")
    public void notification() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();

        String token;

        // Initialize an API Methods client with the given token
        MethodsClient methods = slack.methods(token);


        GetCommitCount getCommitCount = new GetCommitCount();
        try {

            if (getCommitCount.committed(minCommit)) {
                return;
            }
            else {
                String message = "✍️ " + minCommit + "회 이상 커밋하지 않았습니다.\n"
                                + ":dizzy: 1Day " + minCommit + "Commit :dizzy:";
                ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                        .channel(channelId)
                        .text(message)
                        .build();
                ChatPostMessageResponse response = methods.chatPostMessage(request);
            }
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }
}

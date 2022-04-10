package com.CommitAlarmSlackBot.Slack;

import com.CommitAlarmSlackBot.Github.GetCommitCount;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class CommitNotification {

    private final int minCommit = 1;

    @Value("${slack.channelID}")
    private String channelId;
    @Value("${slack.token}")
    private String token;

    @Autowired
    GetCommitCount getCommitCount;

//    @Scheduled(cron = "* * 20 * * ? ")
    @Scheduled(cron = "*/10 * * * * ? ")
    public void notification() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(token);

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

package com.CommitAlarmSlackBot;

import com.slack.api.methods.SlackApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CommitAlarmSlackBotApplication {

	public static void main(String[] args) throws SlackApiException, IOException {
		SpringApplication.run(CommitAlarmSlackBotApplication.class, args);
	}

}

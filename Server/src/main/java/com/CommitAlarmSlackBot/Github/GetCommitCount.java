package com.CommitAlarmSlackBot.Github;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class GetCommitCount {

    GitHub gitHub;

    @Value("${github.token}")
    private String token;

    @Value("${github.username}")
    private String username;

    private void connectGithub () throws IOException {
        gitHub = new GitHubBuilder().withOAuthToken(token).build();
        gitHub.checkApiUrlValidity();
    }
    public boolean committed(int minCommit) throws IOException {
        try {
            connectGithub();
        } catch (IOException e) {
            throw new IllegalArgumentException("Connection failed(Github)");
        }
        LocalDate today = LocalDate.now();
        GHCommitSearchBuilder builder = gitHub.searchCommits().authorName(username).authorDate(today.toString());

        return builder.list().getTotalCount() >= minCommit;
    }

}
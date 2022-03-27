package com.CommitAlarmSlackBot.Github;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDate;

public class GetCommitHistory {

    GitHub gitHub;

    private String token;
    private String userId;

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
        GHCommitSearchBuilder builder = gitHub.searchCommits().authorName(userId).authorDate(today.toString());
       PagedIterable<GHCommit> pagedIterable = builder.list();

        return builder.list().getTotalCount() >= minCommit;
    }

}
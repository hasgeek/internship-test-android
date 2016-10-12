package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {

    @SerializedName("name")
    private String repoName;

    @SerializedName("html_url")
    private String repoUrl;

    @SerializedName("stargazers_count")
    private int repoStars;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public int getRepoStars() {
        return repoStars;
    }

    public void setRepoStars(int repoStars) {
        this.repoStars = repoStars;
    }


    public Repository() {
    }

    public Repository(String repoName, String repoUrl, int repoStars) {
        this.repoName = repoName;
        this.repoUrl = repoUrl;
        this.repoStars = repoStars;
    }
}

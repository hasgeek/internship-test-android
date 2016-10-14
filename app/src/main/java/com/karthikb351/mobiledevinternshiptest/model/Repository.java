package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 *  edited by Gagan Gupta
 */

public class Repository {

    private String full_name;
    private String watchers;
    private String stargazers_count;
    private String open_issues_count;

    /* storing values through constructor*/

    public Repository(String full_name, String watchers, String open_issues_count, String  stargazers_count) {
        this.full_name = full_name;
        this.watchers = watchers;
        this.stargazers_count = stargazers_count;
        this.open_issues_count = open_issues_count;
    }

    /* Getters */

    public String getStargazers_count() {
        return stargazers_count;
    }

    public String getWatchers() {
        return watchers;
    }

    public String getOpen_issues_count() {
        return open_issues_count;
    }

    public String getFull_name() {
        return full_name;
    }

}

package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {

    @SerializedName("full_name")
    private String full_name;
    @SerializedName("html_url")
    private String url;

    public Repository(String full_name, String url) {
        this.full_name = full_name;
        this.url = url;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUrl() {
        return url;
    }
}

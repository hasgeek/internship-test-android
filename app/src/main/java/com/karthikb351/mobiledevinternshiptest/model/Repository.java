package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {


    //Creating a POJO with 3 fields. Variable names same as JSon keys.

    String full_name;
    String description;
    String created_at;

    public Repository(String full_name, String description, String created_at) {
        this.full_name = full_name;
        this.description = description;
        this.created_at = created_at;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

}

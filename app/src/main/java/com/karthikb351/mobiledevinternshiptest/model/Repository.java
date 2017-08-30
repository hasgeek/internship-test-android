package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {
    //Bind GSON to the Response in Retrofit
//    @SerializedName("full_name")
    private String full_name;

    public String getFullName() {
        return full_name;
    }
}

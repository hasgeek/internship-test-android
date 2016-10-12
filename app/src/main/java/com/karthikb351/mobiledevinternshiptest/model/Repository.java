package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String description;

    public Repository(String id,String name,String fullName,String description) {
        //throw new RuntimeException("You need to fill out the fields in this class so GSON and Retrofit can bind to them");
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
    }

    public String getFullName(){
        return fullName;
    }
}
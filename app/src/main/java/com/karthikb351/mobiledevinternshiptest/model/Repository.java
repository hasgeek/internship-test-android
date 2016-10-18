package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {

    //Mapping the JSON response to fields in the model

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("description")
    private String mDescription;

    public Repository(String id, String name, String fullName, String description) {

        //throw new RuntimeException("You need to fill out the fields in this class so GSON and Retrofit can bind to them");

        mId = id;
        mName = name;
        mFullName = fullName;
        mDescription = description;
    }

    /*

    THESE GETTERS WON'T BE USED IN THIS PROJECT SO WE'RE COMMENTING IT OUT

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }*/


    public String getFullName() {
        return mFullName;
    }

}

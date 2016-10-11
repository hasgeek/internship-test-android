package com.karthikb351.mobiledevinternshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class Repository {

    /**
     * I have not included all the fields from the response as we were displaying only the full_name for the repo.
     */

    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}

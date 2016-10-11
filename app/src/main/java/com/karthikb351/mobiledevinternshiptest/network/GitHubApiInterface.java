package com.karthikb351.mobiledevinternshiptest.network;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public interface GitHubApiInterface {

    @GET("orgs/{org}/repos")
    Call<ArrayList<Repository>> getReposByOrg(@Path("org") String org);  //Changing Observable to Call


}

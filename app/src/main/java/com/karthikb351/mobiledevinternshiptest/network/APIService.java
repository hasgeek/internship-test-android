package com.karthikb351.mobiledevinternshiptest.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 * edited by Gagan Gupta
 */

public class APIService {

    GitHubApiInterface service;

    public APIService() {
        //throw new RuntimeException("Build a Retrofit adapter from the GitHubApiInterface with RxJava Observable support as well a GSON parser");

        /* Building Retrofit adapter with RxJava Support Observable support and GSON parser */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        /* Created GitHubApiInterface */
        service = retrofit.create(GitHubApiInterface.class);
    }

    /* returning registered service */
    public GitHubApiInterface getService() {
        return service;
    }
}

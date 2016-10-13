package com.karthikb351.mobiledevinternshiptest.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class APIService {

    GitHubApiInterface service;

    public APIService() {
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build();
        service = retrofit.create(GitHubApiInterface.class);
    }

    public GitHubApiInterface getService() {
        return service;
    }
}

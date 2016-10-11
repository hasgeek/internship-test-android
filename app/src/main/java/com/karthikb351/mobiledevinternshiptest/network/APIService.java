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
        throw new RuntimeException("Build a Retrofit adapter from the GitHubApiInterface with RxJava Observable support as well a GSON parser");
    }

    public GitHubApiInterface getService() {
        return service;
    }
}

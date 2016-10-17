package com.karthikb351.mobiledevinternshiptest.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class APIService {

    /**
     * Made APIService singleton so that only one instance of service is available at all times.
     */

    private static GitHubApiInterface service;

    /**
     * Create a new instance of Retrofit object and added RXJavaAdapterFactory which uses RxJava for creating observables.
     * Added the base URL for making API calls to github.
     * Added GSON as the default parser for the response received from the API call so that we won't have to go through the hassle
     * of parsing the response manually.
     */
    private APIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
        service = retrofit.create(GitHubApiInterface.class);
    }

    public static GitHubApiInterface getService() {
        if (service == null) {
            new APIService();
        }
        return service;
    }
}

package com.karthikb351.mobiledevinternshiptest.network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class APIService {

    GitHubApiInterface service;
    public static final String BASE_URL = "https://api.github.com/";
    public APIService() {}
    public static GitHubApiInterface createRetrofitClient() {
               Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Log.d("tag", "RetrofitAdapter -- created");
       return retrofit.create(GitHubApiInterface.class);


    }

    public GitHubApiInterface getService() {
        return service;
    }
}

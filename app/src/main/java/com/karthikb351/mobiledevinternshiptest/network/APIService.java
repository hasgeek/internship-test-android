package com.karthikb351.mobiledevinternshiptest.network;

import com.karthikb351.mobiledevinternshiptest.helper.WebConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class APIService {

    Retrofit retrofit;
    GitHubApiInterface service;

    public GitHubApiInterface getService() {

        retrofit  = new Retrofit.Builder().
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl(WebConstants.END_URL).
                build();

        service = retrofit.create(GitHubApiInterface.class);

        return service;
    }
}

package com.karthikb351.mobiledevinternshiptest.network;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public class APIService {

    GitHubApiInterface service;
    Observable<List<Repository>> observable;

    String url = "hasgeek";

    public APIService() {
//        throw new RuntimeException("Build a Retrofit adapter from the GitHubApiInterface with RxJava Observable support
//              as well a GSON parser");

        //instance of Retrofit for making network calls
        Retrofit retrofitAdapter = new Retrofit.Builder()
                .baseUrl("http://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //join the instance
        service = retrofitAdapter.create(GitHubApiInterface.class);

        observable =  service.getReposByOrg(url);
    }

    public GitHubApiInterface getService() {
        return service;
    }

    //Provide access to the Observable
    public Observable<List<Repository>> getObservable() {
        return observable;
    }
}

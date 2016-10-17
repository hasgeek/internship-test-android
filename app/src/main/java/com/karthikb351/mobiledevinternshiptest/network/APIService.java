package com.karthikb351.mobiledevinternshiptest.network;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.util.List;

import okhttp3.OkHttpClient;
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
    public static final String BASE_URL = "https://api.github.com/";

    //        throw new RuntimeException("Build a Retrofit adapter from the GitHubApiInterface" +
//                " with RxJava Observable support as well a GSON parser");
    public APIService() {

    }

    public void createRetrofitClient() {

       // OkHttpClient client = new OkHttpClient.Builder().
               Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(GitHubApiInterface.class);
        return service;
    }

    public Observable<List<Repository>> makeObservable() {
        Observable<List<Repository>> observable = service.getReposByOrg("hasgeek");
        return observable;
    }

    public GitHubApiInterface getService() {
        return service;
    }
}

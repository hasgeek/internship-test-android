package com.karthikb351.mobiledevinternshiptest.network;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author: @karthikb351
 * Project: mobile-dev-internship-test
 */

public interface GitHubApiInterface {

    @GET("orgs/{org}/repos")
    Observable<List<Repository>> getReposByOrg(@Path("org") String org);

}

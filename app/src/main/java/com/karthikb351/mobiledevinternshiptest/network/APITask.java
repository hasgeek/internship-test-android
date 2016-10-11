package com.karthikb351.mobiledevinternshiptest.network;

import android.os.AsyncTask;
import android.util.Log;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piyush on 11-10-2016.
 */

public class APITask extends AsyncTask<String, Void, ArrayList<Repository>> {

    ArrayList<Repository> retVal;
    @Override
    protected ArrayList<Repository> doInBackground(String... params) {

        String url = "https://api.github.com/";


        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();
        GitHubApiInterface gitHubApiInterface = retrofit.create(GitHubApiInterface.class);
         retVal = new ArrayList<>();

        try {
            retVal = gitHubApiInterface.getReposByOrg(params[0]).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return retVal;
    }
}

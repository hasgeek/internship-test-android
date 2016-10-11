package com.karthikb351.mobiledevinternshiptest;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;
import com.karthikb351.mobiledevinternshiptest.network.GitHubApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/*
*  edited by Gagan Gupta
*/

public class MainActivity extends AppCompatActivity {

    private LinearLayout noConnection;
    private GitHubRecyclerViewAdapter mGitHubRecyclerViewAdapter;
    private RecyclerView recyclerView;
    private Subscription subscription;
    private String TAG;
    private String repo_name;
    private Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG = this.getString(R.string.ErrorCheck);

        /* storing the organisation name*/
        repo_name = this.getString(R.string.organisation);

        /* Alternate Layout if connection is not available */
        noConnection = (LinearLayout) findViewById(R.id.noConnection);
        noConnection.setVisibility(View.GONE);

        /* Using custom font */
        tf = Typeface.createFromAsset(this.getAssets(), "fonts/Inconsolata.otf");

        initViews();
        makeApiCall();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    /* Setting adapter */
    private void addReposToAdapter(List<Repository> repos) {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mGitHubRecyclerViewAdapter = new GitHubRecyclerViewAdapter(repos, this, tf);
        recyclerView.setAdapter(mGitHubRecyclerViewAdapter);
    }

    private void makeApiCall() {

        /* Avoiding fetching of data on main thread*/

        subscription = getRepositoryObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    /* To check for errors*/

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        /* Bad URL */
                        if(e.getMessage().equals("HTTP 403 Forbidden"))
                            Toast.makeText(getApplicationContext(), getApplication()
                                    .getString(R.string.AccessDenied), Toast.LENGTH_SHORT).show();

                            /* Internet Not available */
                        else {
                            noConnection.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), getApplication()
                                    .getString(R.string.noConnection), Toast.LENGTH_SHORT).show();
                        }
                    }

                    /* attaching values to the RecyclerView */

                    @Override
                    public void onNext(List<Repository> repositories) {
                        addReposToAdapter(repositories);
                    }
                });

    }

    /* To avoid memory leak by unsubscribing the existing references */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    /* Recieving list from the GithubApiInterface through API service */
    public Observable<List<Repository>> getRepositoryObservable() {
        try {
            return new APIService().getService().getReposByOrg(repo_name);
        } catch (Exception ex) {
            return null;
        }
    }
}

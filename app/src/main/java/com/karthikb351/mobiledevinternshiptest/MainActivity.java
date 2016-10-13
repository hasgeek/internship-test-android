package com.karthikb351.mobiledevinternshiptest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Observable<List<Repository>> listObservable;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //Make sure internet is connected before making this call.
        makeApiCall();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void addReposToAdapter(List<Repository> repos) {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new GitHubRecyclerViewAdapter(repos));
    }

    private void makeApiCall() {
            APIService apiService = new APIService();

            listObservable = apiService.getService().getReposByOrg("hasgeek"); //Name of the organisation.
            listObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Repository>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(List<Repository> repositories) {
                    addReposToAdapter(repositories);
                }
            });

    }

    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        public GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = getLayoutInflater().inflate(R.layout.list_item,parent,false);
            GitHubViewHolder gitHubViewHolder = new GitHubViewHolder(rootView);

            return gitHubViewHolder;
        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
            holder.fullName.setText(data.get(position).getFull_name());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {
            TextView fullName;
            public GitHubViewHolder(View view) {
                super(view);
                fullName = (TextView) view.findViewById(R.id.id);
            }
        }
    }
}

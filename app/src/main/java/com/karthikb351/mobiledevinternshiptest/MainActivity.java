package com.karthikb351.mobiledevinternshiptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karthikb351.mobiledevinternshiptest.helper.WebConstants;
import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;
import com.karthikb351.mobiledevinternshiptest.network.GitHubApiInterface;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
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
        //here make call to the API for data
        APIService apiService = new APIService();
        GitHubApiInterface apiInterface = apiService.getService();
        apiInterface.getReposByOrg(WebConstants.ORG_NAME).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        //finished sending data
                    }

                    @Override
                    public void onError(Throwable e) {
                        //some error occurred

                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        //push data to recycler view adapter
                        addReposToAdapter(repositories);
                    }
                });

    }

    //--------------------------------------------- RecyclerView Adapter----------------------------------

    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        public GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the view for adapter
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            return new GitHubViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
            //here put values into the views
            Repository repository = data.get(position);
            holder.tvName.setText(repository.getName());
        }

        @Override
        public int getItemCount() {
            //return the items size
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {

            TextView tvName;

            public GitHubViewHolder(View view) {
                super(view);
                //here initialise the views
                tvName = (TextView)itemView.findViewById(R.id.tvName);
            }
        }
    }
}

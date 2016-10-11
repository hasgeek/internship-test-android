package com.karthikb351.mobiledevinternshiptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Observable<List<Repository>> listObservable;

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
//        throw new RuntimeException("You need to call the API using the APIService class, subscribe to the Observable you get back, and add the result to the recyclerview adapter via addReposToAdapter()");
        APIService apiService = new APIService();
        listObservable = apiService.getService().getReposByOrg("hasgeek");
        listObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ObserverError", "No Data received");
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        addReposToAdapter(repositories);
                    }
                });
    }

    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new GitHubViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
            holder.full_name.setText(data.get(position).getFullName());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class GitHubViewHolder extends RecyclerView.ViewHolder {
            TextView full_name;

            GitHubViewHolder(View view) {
                super(view);
                full_name = (TextView) view.findViewById(R.id.id);
            }
        }
    }
}

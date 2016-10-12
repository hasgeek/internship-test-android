package com.karthikb351.mobiledevinternshiptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    APIService apiService;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = new APIService();

        initViews();
        makeApiCall("hasgeek");
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void addReposToAdapter(List<Repository> repos) {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new GitHubRecyclerViewAdapter(repos));
    }

    private void makeApiCall(String organization) {
        Observable<List<Repository>> call = apiService.getService().getReposByOrg(organization);

        subscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final List<Repository> repositories) {
                        // Added Ordering by no of Stars
                        Collections.sort(repositories, new Comparator<Repository>() {
                            @Override
                            public int compare(Repository repository, Repository t1) {
                                return t1.getRepoStars() - repository.getRepoStars();
                            }
                        });
                        addReposToAdapter(repositories);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        this.subscription.unsubscribe();

        super.onDestroy();
    }

    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        public GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

            GitHubViewHolder gitHubViewHolder = new GitHubViewHolder(v);

            return gitHubViewHolder;
        }

        @Override
        public void onBindViewHolder(final GitHubViewHolder holder, int position) {
            holder.tvRepoName.setText(data.get(position).getRepoName());
            holder.tvRepoStars.setText(String.valueOf(data.get(position).getRepoStars()));

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = data.get(holder.getAdapterPosition()).getRepoUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {

            TextView tvRepoName;
            TextView tvRepoStars;
            View view;

            public GitHubViewHolder(View view) {
                super(view);

                this.view = view;
                tvRepoName = (TextView) view.findViewById(R.id.id);
                tvRepoStars = (TextView) view.findViewById(R.id.textView2);
            }
        }
    }
}

package com.karthikb351.mobiledevinternshiptest;

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
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    APIService apiService;

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
//        throw new RuntimeException("You need to call the API using the APIService class,
//          subscribe to the Observable you get back, and add the result to the recyclerview adapter via addReposToAdapter()");

        apiService = new APIService();

        //subscribe to the observable
        //add the result to the recyclerView adapter via addReposToAdapter()
        apiService.getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Repository>>() {
                    @Override
                    public void call(List<Repository> repositories) {
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
//            throw new RuntimeException("Create and return a GitHubViewHolder object based on the list_item.xml file");

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            GitHubViewHolder holder = new GitHubViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
//            throw new RuntimeException("You should bind the 'full_name' of the repository at this position to the viewholder's textview");

            holder.repoName.setText(data.get(position).getFullName());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {

            private TextView repoName;

            public GitHubViewHolder(View view) {
                super(view);
//                throw new RuntimeException("Follow the ViewHolder pattern and create a ViewHolder from the list_item.xml view");

                repoName = (TextView) itemView.findViewById(R.id.repo_name);
            }
        }
    }
}

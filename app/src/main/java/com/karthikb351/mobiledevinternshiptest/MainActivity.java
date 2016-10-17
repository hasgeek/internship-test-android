package com.karthikb351.mobiledevinternshiptest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;
import com.karthikb351.mobiledevinternshiptest.network.GitHubApiInterface;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Observer<List<Repository>> subscription;
    RecyclerView recyclerView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        makeApiCall();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void addReposToAdapter(List<Repository> repos) {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new GitHubRecyclerViewAdapter(repos));
    }

    private void makeApiCall() {

        // calling the API using the APIService class,
        // subscribing to the Observable , and adding the result to the recyclervie adapter via addReposToAdapter()");
        final GitHubApiInterface service = APIService.createRetrofitClient();

        subscription = (Observer<List<Repository>>) service.getReposByOrg("hasgeek")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
    }

    Observer<List<Repository>> myObserver = new Observer<List<Repository>>() {

        @Override
        public void onCompleted() {

            Log.d("completed", "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d("onError", ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(List<Repository> repositories) {
            addReposToAdapter(repositories);
        }
    };
}

class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

    List<Repository> data = new ArrayList<>();


    public GitHubRecyclerViewAdapter(List<Repository> repos) {
        this.data = repos;
    }


    @Override
    public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create and return a GitHubViewHolder object based on the list_item.xml file;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        GitHubViewHolder viewHolder = new GitHubViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GitHubViewHolder holder, int position) {
        //bind the 'full_name' of the repository at this position to the viewholder's textview
        Repository repo = data.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.itemName;
        textView.setText(repo.getFullName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GitHubViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public GitHubViewHolder(View view) {
            super(view);
            // create a ViewHolder from the list_item.xml view;
            itemName = (TextView) view.findViewById(R.id.id);
        }
    }
}


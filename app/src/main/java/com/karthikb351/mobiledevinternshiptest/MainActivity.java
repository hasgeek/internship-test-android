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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Observable<List<Repository>> listObservable;
    Subscription subscription;

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

    /**
     * Unsubscribe from the observable when the activity is about to be destroyed.
     * This is mainly done to prevent memory leaks.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    /**
     * Make the API Call and get an Observable which contains a list of Repository Names.
     * Also subscribed to this observable and added the List of Repo Names to the Adapter.
     * I also Added Logging statements to onCompleted() and onError() methods for easier debugging.
     */

    private void makeApiCall() {
        listObservable = APIService.getService().getReposByOrg("hasgeek");
        /**
         * Use the dedicated io thread instead of creating a new one
         */
        listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        Log.v(getClass().getSimpleName(), "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
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

        /**
         * Received the full name of the repository for a particular position from the list of Repositories
         * and set it to the TextView which is initialized in the ViewHolder
         */

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
            holder.fullName.setText(data.get(position).getFullName());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        /**
         * Initialized the TextView in list_item.xml which will display the full name of the repository.
         */

        class GitHubViewHolder extends RecyclerView.ViewHolder {
            TextView fullName;

            GitHubViewHolder(View view) {
                super(view);
                fullName = (TextView) view.findViewById(R.id.id);
            }
        }
    }
}

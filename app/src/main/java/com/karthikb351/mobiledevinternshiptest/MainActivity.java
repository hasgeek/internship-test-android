package com.karthikb351.mobiledevinternshiptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APIService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


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
        Repository repository=new Repository();
        APIService apiService = new APIService();
        // throw new RuntimeException("You need to call the API using the APIService class,
        // subscribe to the Observable you get back, and add the result to the recyclerview
        // adapter via addReposToAdapter()");
        apiService.getService();
apiService.makeObservable().subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
        apiService.makeObservable().subscribe(new Observer<List<Repository>>(){
            @Override
            public void onCompleted(){
                Log.d("Observable", "onCompleted: "+repository.getFullName());
            }

            @Override
            public void onError(Throwable e){
                //handle error
                Log.e("Observable", "not");
            }

            @Override
            public void onNext(List<Repository> response){
                //handle response
                Log.d("Observable", "onnext "+repository.getFullName());
            }
        });
//        .map(user->"Repo Full_name:"+ repository.getFullName())
//        .subscribe(fullName-> Log.d("Info", "makeApiCall:"+ fullName));
        Log.d("APISERVICE", "makeApiCall: "+apiService.makeObservable().toString());
    }
    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        public GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            throw new RuntimeException("Create and return a GitHubViewHolder object based on the list_item.xml file");
        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {
            throw new RuntimeException("You should bind the 'full_name' of the repository at this position to the viewholder's textview");
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {

            public GitHubViewHolder(View view) {
                super(view);
                throw new RuntimeException("Follow the ViewHolder pattern and create a ViewHolder from the list_item.xml view");
            }
        }
    }
}

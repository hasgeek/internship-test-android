package com.karthikb351.mobiledevinternshiptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.karthikb351.mobiledevinternshiptest.model.Repository;
import com.karthikb351.mobiledevinternshiptest.network.APITask;
import java.util.ArrayList;
import java.util.List;

/*


Created an Asyn Task APITask instead of APIService.
Used Callback insted RxJava

Added card view for better UI
 */



public class MainActivity extends AppCompatActivity {

    ArrayList<Repository> repos;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        repos = new ArrayList<>();


        initViews();


        //Make sure internet is connected before making this call. Or else check by adding check_network_state permssion in manifest.
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



        new APITask() {
            @Override
            protected void onPostExecute(ArrayList<Repository> repositories) {
                super.onPostExecute(repositories);
                repos = repositories;
                addReposToAdapter(repos);
            }
        }.execute("hasgeek"); //Name of the organisation.

    }

    class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

        List<Repository> data = new ArrayList<>();

        public GitHubRecyclerViewAdapter(List<Repository> repos) {
            this.data = repos;
        }


        @Override
        public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View rootView = getLayoutInflater().inflate(R.layout.list_item, parent, false);

            GitHubViewHolder retVal = new GitHubViewHolder(rootView);

            retVal.name = (TextView) rootView.findViewById(R.id.tv_Name);
            retVal.description = (TextView) rootView.findViewById(R.id.tv_Description);
            retVal.createdAt = (TextView) rootView.findViewById(R.id.tv_CreatedAt);

            return retVal;

        }

        @Override
        public void onBindViewHolder(GitHubViewHolder holder, int position) {

            Repository repository = data.get(position);

            holder.name.setText(String.valueOf(repository.getFull_name()));
            holder.description.setText(String.valueOf(repository.getDescription()));
            holder.createdAt.setText(String.valueOf(repository.getCreated_at()));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class GitHubViewHolder extends RecyclerView.ViewHolder {


            TextView name;
            TextView description;
            TextView createdAt;

            public GitHubViewHolder(View view) {
                super(view);

            }
        }
    }
}

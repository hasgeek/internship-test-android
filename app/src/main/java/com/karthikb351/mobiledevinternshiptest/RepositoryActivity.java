package com.karthikb351.mobiledevinternshiptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RepositoryActivity extends AppCompatActivity {
    TextView tv_full_name,tv_description,tv_created_at,tv_forks_count,tv_git_url,tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        tv_full_name= (TextView) findViewById(R.id.tv_full_name);
        tv_description= (TextView) findViewById(R.id.tv_description);
        tv_forks_count= (TextView) findViewById(R.id.tv_forks_count);
        tv_created_at= (TextView) findViewById(R.id.tv_created_at);
        tv_git_url= (TextView) findViewById(R.id.tv_git_url);
        tv_name= (TextView) findViewById(R.id.tv_name);
        Intent intent = getIntent();
        String fullName=intent.getStringExtra("full_name");
        String description=intent.getStringExtra("description");
        String createdAt=intent.getStringExtra("createdAt");
        int forksCount=intent.getIntExtra("forksCount",0);
        String gitUrl=intent.getStringExtra("gitUrl");
        String name=intent.getStringExtra("name");

        tv_full_name.setText(fullName);
        tv_description.setText(description);
        tv_created_at.setText(createdAt);
        tv_forks_count.setText(String.valueOf(forksCount));
        tv_git_url.setText(gitUrl);
        tv_name.setText(name);



    }
}

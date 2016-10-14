package com.karthikb351.mobiledevinternshiptest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karthikb351.mobiledevinternshiptest.model.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 10/11/2016.
 */

class GitHubRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRecyclerViewAdapter.GitHubViewHolder> {

    List<Repository> data = new ArrayList<>();
    private LayoutInflater mInflater;
    private Typeface tf;

    public GitHubRecyclerViewAdapter(List<Repository> repos, Activity mActivity, Typeface tf) {
        this.data = repos;
        this.tf = tf;
        mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public GitHubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.list_item, parent, false);
        GitHubViewHolder vh = new GitHubViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GitHubViewHolder holder, int position) {

        /*setting values */

        holder.getmTextView().setText(data.get(position).getFull_name());
        holder.getmStars().setText(data.get(position).getStargazers_count());
        holder.getmIssues().setText(data.get(position).getOpen_issues_count());

        /*setting fonts*/

        holder.getmTextView().setTypeface(tf);
        holder.getmIssues().setTypeface(tf);
        holder.getmStars().setTypeface(tf);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /* Inner class to set-up each list item */
    public class GitHubViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mStars;
        private TextView mIssues;

        //Constructor setting the value of the TextView
        public GitHubViewHolder(View view) {
            super(view);
            mStars = (TextView) view.findViewById(R.id.star_count);
            mIssues = (TextView) view.findViewById(R.id.issues_count);
            mTextView = (TextView) view.findViewById(R.id.id);
        }

        /*Getters and Setters*/

        public TextView getmTextView() {
            return mTextView;
        }
        public TextView getmIssues() {
            return mIssues;
        }
        public TextView getmStars() {
            return mStars;
        }


    }
}
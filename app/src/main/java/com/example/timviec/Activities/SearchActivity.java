package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Adapter.JobAdapter;
import com.example.timviec.DataFromInternet;
import com.example.timviec.Model.Job;
import com.example.timviec.R;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ImageView back;
    private RecyclerView rcv_jobs;
    private JobAdapter jobAdapter;
    private List<Job> mList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchJob);
        rcv_jobs = findViewById(R.id.seach_rcv_jobs);
        back = findViewById(R.id.backToHome);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Job> newList = (List<Job>) bundle.get("listJobs");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_jobs.setLayoutManager(linearLayoutManager);
        jobAdapter = new JobAdapter();
        try {
            jobAdapter.setData(getListJob());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                jobAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                jobAdapter.getFilter().filter(s);
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rcv_jobs.setAdapter(jobAdapter);

    }
    private List<Job> getListJob() throws IOException {
        return null;
    }
}
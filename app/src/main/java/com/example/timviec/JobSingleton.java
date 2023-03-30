package com.example.timviec;

import com.example.timviec.Model.Job;

public class JobSingleton {
    private static JobSingleton instance;
    private Job job;

    private JobSingleton() {}

    public static JobSingleton getInstance() {
        if (instance == null) {
            instance = new JobSingleton();
        }
        return instance;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}

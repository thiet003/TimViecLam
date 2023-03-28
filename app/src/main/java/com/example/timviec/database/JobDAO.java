package com.example.timviec.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timviec.Model.Job;

import java.util.List;

@Dao
public interface JobDAO {
    @Insert
    void insertJob(Job job);
    @Query("SELECT * FROM job")
    List<Job> getListJob();
    @Query("DELETE FROM job")
    void deleteAllJob();
}

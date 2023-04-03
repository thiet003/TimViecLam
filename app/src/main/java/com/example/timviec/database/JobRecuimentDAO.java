package com.example.timviec.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timviec.Model.JobRecuiment;

import java.util.List;
@Dao
public interface JobRecuimentDAO {
    @Insert
    void insertRecuiment(JobRecuiment jobRecuiment);

    @Query("SELECT * FROM jobRecuiment")
    List<JobRecuiment> getListRecuimentJob();

    @Query("SELECT * FROM jobRecuiment WHERE link =:link")
    List<JobRecuiment> checkRecuimentJob(String link);

    @Query("DELETE FROM jobRecuiment WHERE link = :link")
    void deleteRecuimentJob(String link);

    @Query("DELETE FROM jobRecuiment")
    void deteleAll();
    @Query("SELECT COUNT(*) FROM jobRecuiment")
    int getJRCount();
}

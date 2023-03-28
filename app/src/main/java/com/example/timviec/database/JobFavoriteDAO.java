package com.example.timviec.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timviec.Model.JobFavorite;

import java.util.List;

@Dao
public interface JobFavoriteDAO {
    @Insert
    void insertFavoriteJob(JobFavorite jobFavorite);

    @Query("SELECT * FROM jobFavorite")
    List<JobFavorite> getListFavoriteJob();

    @Query("SELECT * FROM jobFavorite WHERE link =:link")
    List<JobFavorite> checkFavoriteJob(String link);

    @Query("DELETE FROM jobFavorite WHERE link = :link")
    void deleteFavoriteJob(String link);

    @Query("DELETE FROM jobFavorite")
    void deteleAll();
}

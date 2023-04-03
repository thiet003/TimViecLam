package com.example.timviec.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timviec.Model.JobFavorite;
import com.example.timviec.Model.User;

import java.util.List;
@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user WHERE userName =:username")
    List<User> checkUser(String username);

    @Query("DELETE FROM user WHERE userName = :username")
    void deleteFavoriteJob(String username);

    @Query("DELETE FROM user")
    void deteleAll();
    @Update
    void updateUser(User user);
}

package com.example.timviec.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timviec.DataFromInternet;
import com.example.timviec.Model.Job;

@Database(entities = {Job.class}, version = 1)
public abstract class JobDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "job_db";
    private static JobDatabase instance;

    public static synchronized JobDatabase getInstance(Context context)
    {
        if (instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),JobDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


    public abstract JobDAO jobDAO();
}

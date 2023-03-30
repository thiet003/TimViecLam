package com.example.timviec.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timviec.Model.JobRecuiment;

@Database(entities = {JobRecuiment.class},version = 1)
public abstract class JobRecuimentDatabase extends RoomDatabase{
    private static final String DATABASE_NAME = "rjob.db";
    private static JobRecuimentDatabase instance;
    public static synchronized JobRecuimentDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),JobRecuimentDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract JobRecuimentDAO jobRecuimentDAO();
}

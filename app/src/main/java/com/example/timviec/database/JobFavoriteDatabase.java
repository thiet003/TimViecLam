package com.example.timviec.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timviec.Model.JobFavorite;

@Database(entities = {JobFavorite.class}, version = 1)
public abstract class JobFavoriteDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "fjob.db";
    private static JobFavoriteDatabase instance;

    public static synchronized JobFavoriteDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),JobFavoriteDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract JobFavoriteDAO jobFavoriteDAO();
}

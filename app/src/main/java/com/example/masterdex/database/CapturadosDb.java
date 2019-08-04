package com.example.masterdex.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.masterdex.models.Pokemon;

@Database(entities = Pokemon.class, version = 1)
public abstract class CapturadosDb extends RoomDatabase {

    private static CapturadosDb database;


    public abstract CapturadosDao capturadosDao();

    public static CapturadosDb getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), CapturadosDb.class, "Capturados_Db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }


}
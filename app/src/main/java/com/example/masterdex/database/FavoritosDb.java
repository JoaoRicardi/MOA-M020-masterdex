package com.example.masterdex.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;


import com.example.masterdex.models.Pokemon;

@Database(entities = Pokemon.class, version = 1)
public abstract class FavoritosDb extends RoomDatabase {

    private static FavoritosDb favoritosDb;


    public abstract FavoritosDao favoritosDao();

    public static FavoritosDb getInstance(Context context) {
        if (favoritosDb == null) {
            favoritosDb = Room.databaseBuilder(context.getApplicationContext(), FavoritosDb.class, "Favoritos_Db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return favoritosDb;
    }


}
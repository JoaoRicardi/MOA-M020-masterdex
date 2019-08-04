package com.example.masterdex.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;


import com.example.masterdex.models.Pokemon;

@Database(entities = Pokemon.class, version = 1)
public abstract class MasterdexDatabase extends RoomDatabase {

    private static MasterdexDatabase masterdexDatabase;


    public abstract PokemonDao pokemonDao();

    public static MasterdexDatabase getInstance(Context context){
        if (masterdexDatabase == null){
            masterdexDatabase = Room.databaseBuilder(context.getApplicationContext(),MasterdexDatabase.class,"MASTERDEX_DATABASE")
           .fallbackToDestructiveMigration()
           .build();
        }
        return masterdexDatabase;
    }


}
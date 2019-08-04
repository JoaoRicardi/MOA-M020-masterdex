package com.example.masterdex.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.masterdex.models.Pokemon;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface CapturadosDao {

    @Query("SELECT * FROM pokemons ORDER BY url ASC ")
    Observable<List<Pokemon>> getAll();

    @Query("SELECT * FROM pokemons WHERE id = :id")
    Pokemon getId(int id);

    @Query("SELECT * FROM pokemons WHERE number = :number")
    Observable<Pokemon> getNumber(int number);

    @Query("SELECT * FROM pokemons WHERE name = :name")
    Observable<Pokemon> getName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pokemon pokemon);

    @Update
    void update(Pokemon pokemon);

    @Query("DELETE FROM pokemons WHERE name = :name")
    void deleteByName(String name);
}
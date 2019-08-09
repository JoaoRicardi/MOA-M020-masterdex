package com.example.masterdex.repository;

import android.content.Context;
import android.widget.ToggleButton;

import androidx.room.Room;

import com.example.masterdex.database.CapturadosDao;
import com.example.masterdex.database.CapturadosDb;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.service.RetrofitService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetalhesPokemonRepository {

    private RetrofitService retrofitService = new RetrofitService();
    private FavoritosDb favoritosDb;
    private CapturadosDb capturadosDb;
    private ToggleButton botaoFavorito;
    private ToggleButton botaoCapturado;
    public static final String FAVORITOS_DB = "favoritos_Db";
    public static final String CAPTURADOS_DB = "capturados_Db";


    public DetalhesPokemonRepository(Context context){
        favoritosDb = Room.databaseBuilder(context, FavoritosDb.class, FAVORITOS_DB).build();
        capturadosDb = Room.databaseBuilder(context, CapturadosDb.class, CAPTURADOS_DB).build();

    }


    public void deletarPokemonFavorito(Pokemon pokemon) {

        Completable.fromAction(() -> favoritosDb.favoritosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void inserirPokemonFavorito(Pokemon pokemon) {
        Completable.fromAction(() -> favoritosDb.favoritosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deletarPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void inserirPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void consultaPokemonCapturado(Pokemon pokemon) {
        CapturadosDao capturadosDao = capturadosDb.capturadosDao();
        capturadosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoCapturado.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoCapturado.setChecked(false);
                    }
                });
    }

    public void consultaPokemonFavoritado(Pokemon pokemon) {
        FavoritosDao favoritosDao = favoritosDb.favoritosDao();
        favoritosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoFavorito.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoFavorito.setChecked(false);
                    }
                });
    }
}

package com.example.masterdex.repository;

import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.PokemonResponse;
import com.example.masterdex.service.RetrofitService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class PokemonRepository {

    private RetrofitService retrofitService = new RetrofitService();



    public Observable<List<Pokemon>> getPokeListApi (int limit, int offset){
        return retrofitService.getPokeApi()
                .getPokemon(limit,offset)
                .map(PokemonResponse::getResults);

    }

    public Observable<Pokemon> getPokemonByName(String name) {
        return retrofitService.getPokeApi()
                .getPokemonByName(name);
    }
}

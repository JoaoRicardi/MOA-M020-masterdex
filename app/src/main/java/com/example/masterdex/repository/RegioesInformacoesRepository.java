package com.example.masterdex.repository;

import com.example.masterdex.models.Regiao;
import com.example.masterdex.models.RegiaoPokemon;
import com.example.masterdex.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RegioesInformacoesRepository {

    private RetrofitService retrofitService = new RetrofitService();

    private static final String SORT = "id:asc";

    public Observable<List<RegiaoPokemon>> getPokemonRegionList(int limit){
        return retrofitService.getPokeApi()
                .getPokemonByRegion(10,  SORT)
                .map(regiaoResponse -> regiaoResponse.getRegionPokemonResults());
    }
}

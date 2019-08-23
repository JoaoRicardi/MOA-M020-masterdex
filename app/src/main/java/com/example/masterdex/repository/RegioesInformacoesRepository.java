package com.example.masterdex.repository;

import com.example.masterdex.models.Regiao;
import com.example.masterdex.models.RegiaoPokemon;
import com.example.masterdex.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RegioesInformacoesRepository {

    private RetrofitService retrofitService = new RetrofitService();

    public Observable<List<RegiaoPokemon>> getPokemonRegionList(String nomeRegiao, int limit, String sort){
        return retrofitService.getPokeApi()
                .getPokemonByRegion(nomeRegiao, limit, sort)
                .map(regiaoResponse -> regiaoResponse.getPokemonEntries());
    }
}

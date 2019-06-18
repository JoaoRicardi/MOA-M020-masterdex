package com.example.masterdex.pokeApi;

import com.example.masterdex.models.PokemonResposta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

        @GET("pokemon?offset=0&limit=964")
        Call<PokemonResposta> obterListaPokemon();

}

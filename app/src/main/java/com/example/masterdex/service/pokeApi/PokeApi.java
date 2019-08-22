package com.example.masterdex.service.pokeApi;

import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {

    @GET("pokemon?offset=0&limit=964")
    Observable<PokemonResponse> getPokemon(@Query("limit") int limit,
                                           @Query("offset") int offset);

    @GET("pokemon/{pokemonNome}")
    Observable<Pokemon> getPokemonByName(@Path("pokemonNome") String pokemonNome);

    @GET("pokemon-species/{pokemonNome}")
    Observable<Pokemon> getPokemonSpecieByName(@Path("pokemonNome") String pokemonNome);
}

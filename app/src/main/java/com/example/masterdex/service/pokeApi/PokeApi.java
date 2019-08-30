package com.example.masterdex.service.pokeApi;

import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.PokemonResponse;
import com.example.masterdex.models.RegiaoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {

    @GET("pokemon?offset=0&limit=897")
    Observable<PokemonResponse> getPokemon(@Query("limit") int limit,
                                           @Query("offset") int offset);

    @GET("pokemon/{pokemonNome}")
    Observable<Pokemon> getPokemonByName(@Path("pokemonNome") String pokemonNome);

    @GET("pokemon-species/{pokemonNome}")
    Observable<Pokemon> getPokemonSpecieByName(@Path("pokemonNome") String pokemonNome);

    @GET("pokedex/{regiaoNome}")
    Observable<RegiaoResponse> getPokemonByRegion (@Path("regiaoNome") String regiaoNome,
                                                   @Query("limit") int limitRegiao,
                                                   @Query("sort")  String sort);
}

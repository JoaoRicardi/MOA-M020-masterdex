package com.example.masterdex.service.pokeApi;

import com.example.masterdex.models.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {

        @GET("pokemon?offset=0&limit=964")
        Observable<PokemonResponse> getPokemon (@Query("limit") int limit,
                                                @Query("offset") int offset);


}

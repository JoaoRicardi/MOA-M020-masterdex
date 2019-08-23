package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegiaoResponse {

    @SerializedName("pokemon_entries")
    private List<RegiaoPokemon> pokemonEntries;

    public List<RegiaoPokemon> getPokemonEntries() {
        return pokemonEntries;
    }

    public void setPokemonEntries(List<RegiaoPokemon> pokemonEntries) {
        this.pokemonEntries = pokemonEntries;
    }
}

package com.example.masterdex.models;

import java.util.List;

public class RegiaoResponse {

    private List<RegiaoPokemon> regionPokemonResults;

    public List<RegiaoPokemon> getRegionPokemonResults() {
        return regionPokemonResults;
    }

    public void setRegionPokemonResults(List<RegiaoPokemon> regionPokemonResults) {
        this.regionPokemonResults = regionPokemonResults;
    }
}

package com.example.masterdex.models;


import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {


    private List<PokemonApi> results; // armazenando no arrays o arquivo  json


    public List<PokemonApi> getResults() {
        return results; //metodo que retorna os objetos
    }

    public void setResults(ArrayList<PokemonApi> results) {
        this.results = results;// metodo que adiciona os objetos
    }
}

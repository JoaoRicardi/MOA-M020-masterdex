package com.example.masterdex.models;


import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {


    private List<Pokemon> results; // armazenando no arrays o arquivo  json


    public List<Pokemon> getResults() {
        return results; //metodo que retorna os objetos
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;// metodo que adiciona os objetos
    }
}

package com.example.masterdex.models;


import java.util.ArrayList;

public class PokemonResposta {


    ArrayList<Pokemon> results; // armazenando no arrays o arquivo  json


    public ArrayList<Pokemon> getResults() {
        return results; //metodo que retorna os objetos
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;// metodo que adiciona os objetos
    }
}

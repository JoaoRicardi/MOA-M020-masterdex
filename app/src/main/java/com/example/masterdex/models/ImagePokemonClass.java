package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

public class ImagePokemonClass {

    public String getFotoPokemon() {
        return fotoPokemon;
    }

    public void setFotoPokemon(String fotoPokemon) {
        this.fotoPokemon = fotoPokemon;
    }

    @SerializedName("front_default")
    private String fotoPokemon;

}

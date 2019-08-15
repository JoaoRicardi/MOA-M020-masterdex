package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

class Sprites {

    @SerializedName("front_default")
    private String imagemPokemon;

    public String getImagemPokemon() {
        return imagemPokemon;
    }

    public void setImagemPokemon(String imagemPokemon) {
        this.imagemPokemon = imagemPokemon;
    }
}

package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

public class RegiaoPokemon {

    @SerializedName("entry_number")
    private int entryNumber;

    @SerializedName("pokemon_species")
    private Pokemon pokemon;

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}

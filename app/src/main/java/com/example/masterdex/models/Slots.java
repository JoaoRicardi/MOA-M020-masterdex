package com.example.masterdex.models;

import java.io.Serializable;

public class Slots implements Serializable {

    private TipoPokemon type;

    public TipoPokemon getType() {
        return type;
    }

    public void setType(TipoPokemon type) {
        this.type = type;
    }
}

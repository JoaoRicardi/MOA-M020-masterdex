package com.example.masterdex;

import com.example.masterdex.models.Pokemon;

import java.util.Comparator;

class OrdenaPorNome implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon um, Pokemon dois) {
        return um.getName().compareTo(dois.getName());
    }
}

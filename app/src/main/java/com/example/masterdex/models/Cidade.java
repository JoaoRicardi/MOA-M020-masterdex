package com.example.masterdex.models;

import java.io.Serializable;

public class Cidade implements Serializable {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
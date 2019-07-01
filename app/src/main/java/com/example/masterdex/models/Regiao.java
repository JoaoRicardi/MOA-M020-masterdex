package com.example.masterdex.models;

import android.media.Image;

import java.io.Serializable;

public class Regiao implements Serializable {

    private String nomeRegiao;
    private String imagemRegiao;

    public Regiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public String getImagemRegiao() {
        return imagemRegiao;
    }

    public void setImagemRegiao(String imagemRegiao) {
        this.imagemRegiao = imagemRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }
}

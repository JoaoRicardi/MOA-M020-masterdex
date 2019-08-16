package com.example.masterdex.modules.regioes.model;

import android.media.Image;

import java.io.Serializable;

public class Regiao implements Serializable {

    private String nomeRegiao;
    private String imagemRegiao;
    private String descricaoRegiao;
    private int idRegiao;

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getDescricaoRegiao() {
        return descricaoRegiao;
    }

    public void setDescricaoRegiao(String descricaoRegiao) {
        this.descricaoRegiao = descricaoRegiao;
    }

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

package com.example.masterdex.models;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Regiao implements Serializable {

    private String nomeRegiao;
    private String imagemRegiao;
    private String descricaoRegiao;
    private List<Cidade> cidadeList = new ArrayList<>();

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

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }
}

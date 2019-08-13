package com.example.masterdex.models;

import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TipoCamada1 {


    private List<TipoCamada2> camada2List;


    public List<TipoCamada2> getCamada2List() {
        return camada2List;
    }

    public void setCamada2List(List<TipoCamada2> camada2List) {
        this.camada2List = camada2List;
    }
}

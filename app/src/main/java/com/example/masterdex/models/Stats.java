package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("base_stat")
    private int valorStats;

    public int getValorStats() {
        return valorStats;
    }

    public void setValorStats(int valorStats) {
        this.valorStats = valorStats;
    }
}

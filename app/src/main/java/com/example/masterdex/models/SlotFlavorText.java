package com.example.masterdex.models;

import com.google.gson.annotations.SerializedName;

public class SlotFlavorText {

    @SerializedName("flavor_text")
    private String flavorText;

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }
}

package com.example.masterdex.models;

import java.io.Serializable;

public class TipoCamada2 {

    private int slot;

    private TipoCamada3 type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TipoCamada3 getType() {
        return type;
    }

    public void setType(TipoCamada3 type) {
        this.type = type;
    }
}

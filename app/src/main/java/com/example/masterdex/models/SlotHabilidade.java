package com.example.masterdex.models;

import java.io.Serializable;

public class SlotHabilidade implements Serializable {

    private Habilidade move;

    public Habilidade getMove() {
        return move;
    }

    public void setMove(Habilidade move) {
        this.move = move;
    }
}

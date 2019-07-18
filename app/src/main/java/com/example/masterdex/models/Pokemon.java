package com.example.masterdex.models;

import java.io.Serializable;

public class Pokemon implements Serializable {


    // aki nao tem segredo
    private String name;
    private String url;
    private int number;

    public int getNumber() { String [] urlSplit = url.split("/");
        return Integer.parseInt(urlSplit[urlSplit.length -1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



        package com.example.masterdex.models;

        import androidx.annotation.Nullable;
        import androidx.room.ColumnInfo;
        import androidx.room.Entity;
        import androidx.room.PrimaryKey;

        import java.io.Serializable;

@Entity(tableName = "pokemons")
public class Pokemon implements Serializable {



    // aki nao tem segredo

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "favorito")
    private Boolean favorito = false;

    @ColumnInfo(name = "capturado")
    private Boolean capturado = false;









    public int getNumber() {
        String[] urlSplit = url.split("/");
        return Integer.parseInt(urlSplit[urlSplit.length - 1]);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public Boolean getCapturado() {
        return capturado;
    }

    public void setCapturado(Boolean capturado) {
        this.capturado = capturado;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Pokemon outro = (Pokemon) obj;
        return this.getFavorito().equals(outro.getFavorito());
    }


}



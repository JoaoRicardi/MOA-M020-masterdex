package com.example.masterdex.view;

import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.masterdex.R;
import com.example.masterdex.database.CapturadosDao;
import com.example.masterdex.database.CapturadosDb;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.example.masterdex.models.Pokemon;
import com.squareup.picasso.Picasso;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DetalhesPokemonActivity extends AppCompatActivity {

    public static final String FAVORITOS_DB = "favoritos_Db";
    public static final String CAPTURADOS_DB = "capturados_Db";

    private ToggleButton botaoFavorito;
    private ToggleButton botaoCapturado;
    private FavoritosDb favoritosDb;
    private CapturadosDb capturadosDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        ImageView botaoVoltar = findViewById(R.id.detalhes_pokemon_voltar);
        botaoFavorito = findViewById(R.id.toggle_favorito_Button);
        botaoCapturado = findViewById(R.id.toggle_capturado_Button);
        ImageView imagemPokemon = findViewById(R.id.detalhes_pokemon_image_view);
        TextView nomePokemon = findViewById(R.id.detalhes_pokemon_nome);
        ImageView tipoPokemon = findViewById(R.id.detalhes_pokemon_tipo_image_view);
        TextView descricaoPokemon = findViewById(R.id.detalhes_pokemon_descricao_text_view);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });

        favoritosDb = Room.databaseBuilder(this,
                FavoritosDb.class, FAVORITOS_DB).build();

        capturadosDb = Room.databaseBuilder(this,
                CapturadosDb.class, CAPTURADOS_DB).build();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");
// aki faço o nome do pokemon ficar com a primeira letra maiuscula.


        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));
        nomePokemon.setText(pok);

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png").into(imagemPokemon);
        //como o proprio nome ja diz kkk


        System.out.println(pokemon.getName());
        System.out.println("****************");
        consultaPokemonFavoritado(pokemon);
        consultaPokemonCapturado(pokemon);

        botaoFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    inserirPokemonFavorito(pokemon);
                } else {
                    deletarPokemonFavorito(pokemon);
                }
            }
        });
        botaoCapturado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    inserirPokemonCapturado(pokemon);
                } else {
                    deletarPokemonCapturado(pokemon);
                }
            }
        });
    }

    private void deletarPokemonFavorito(Pokemon pokemon) {
        Completable.fromAction(() -> favoritosDb.favoritosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void inserirPokemonFavorito(Pokemon pokemon) {
        Completable.fromAction(() -> favoritosDb.favoritosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void deletarPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void inserirPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void consultaPokemonCapturado(Pokemon pokemon) {
        CapturadosDao capturadosDao = capturadosDb.capturadosDao();
        capturadosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoCapturado.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoCapturado.setChecked(false);
                    }
                });
    }

    private void consultaPokemonFavoritado(Pokemon pokemon) {
        FavoritosDao favoritosDao = favoritosDb.favoritosDao();
        favoritosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoFavorito.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoFavorito.setChecked(false);
                    }
                });
    }

    private void voltarHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


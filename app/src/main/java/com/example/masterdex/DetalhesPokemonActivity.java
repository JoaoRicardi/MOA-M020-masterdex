package com.example.masterdex;

import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.masterdex.database.MasterdexDatabase;
import com.example.masterdex.database.PokemonDao;
import com.example.masterdex.models.Pokemon;
import com.squareup.picasso.Picasso;

import io.reactivex.Completable;
import io.reactivex.ObservableConverter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DetalhesPokemonActivity extends AppCompatActivity {

    private ToggleButton botaoFavorito;
    private ToggleButton botaoCapturado;
    private MasterdexDatabase db;


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

        db = Room.databaseBuilder(this,
                MasterdexDatabase.class, "MASTERDEX_DATABASE").build();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");
// aki faço o nome do pokemon ficar com a primeira letra maiuscula.
        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));
        nomePokemon.setText(pok);
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png").into(imagemPokemon);
        //como o proprio nome ja diz kkk


        System.out.println("Favoritado");
        System.out.println(pokemon.getFavorito());
        System.out.println("capturado");
        System.out.println(pokemon.getCapturado());
        consultaPokemonBanco(pokemon);
        botaoFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    pokemonFavoritado(pokemon);
                    System.out.println("Favoritado");
                    System.out.println(pokemon.getFavorito());
                    inserirPokemonBanco(pokemon);
                } else {
                    pokemon.setFavorito(false);
                }
            }
        });

        botaoCapturado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    pokemon.setCapturado(true);
                    System.out.println("capturado");
                    System.out.println(pokemon.getCapturado());
                    inserirPokemonBanco(pokemon);
                } else {
                    pokemon.setCapturado(false);

                }
            }
        });
    }

    private Pokemon pokemonFavoritado(Pokemon pokemon) {
        pokemon.setFavorito(true);
        return pokemon;
    }
    private Pokemon pokemonCapturado(Pokemon pokemon) {
        pokemon.setCapturado(true);
        return pokemon;
    }

    private void deletarPokemonBanco(Pokemon pokemon) {
        Completable.fromAction(() -> db.pokemonDao().delete(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void inserirPokemonBanco(Pokemon pokemon) {
        Completable.fromAction(() -> db.pokemonDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
            }

    private void consultaPokemonBanco(Pokemon pokemon) {

        PokemonDao pokemonDao = db.pokemonDao();
        pokemonDao.pegaPeloNome(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    if (pokemonEncontrado.getFavorito().equals(pokemon)){
                        botaoFavorito.setChecked(false);
                    }else{
                        botaoFavorito.setChecked(true);
                    }
                });
    }

    private void atualizarPokemonBanco(Pokemon pokemon) {
        Completable.fromAction(() -> db.pokemonDao().update(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void voltarHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


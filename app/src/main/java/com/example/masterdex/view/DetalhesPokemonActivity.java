package com.example.masterdex.view;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.Layout;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.masterdex.R;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.DetalhesPokemonViewModel;
import com.squareup.picasso.Picasso;


public class DetalhesPokemonActivity extends AppCompatActivity {

    private ToggleButton botaoFavorito;
    private ToggleButton botaoCapturado;
    private ConstraintLayout backgroundPokemon;



    private DetalhesPokemonViewModel detalhesPokemonViewModel;


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
        backgroundPokemon = findViewById(R.id.background_constraint_detalhe_pokemon);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });


        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));
        nomePokemon.setText(pok);

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png").into(imagemPokemon);

        if (pokemon.getName().equals("squirtle")){
            backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_agua));
        } else {
            backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_fogo));
        }

        System.out.println(pokemon.getName());
        System.out.println("****************");
        //detalhesPokemonViewModel.getPokemonRepository().consultaPokemonFavoritado(pokemon);
        //detalhesPokemonViewModel.getPokemonRepository().consultaPokemonCapturado(pokemon);

        botaoFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    detalhesPokemonViewModel.getPokemonRepository().inserirPokemonFavorito(pokemon);
                } else {
                    detalhesPokemonViewModel.getPokemonRepository().deletarPokemonFavorito(pokemon);
                }
            }
        });
        botaoCapturado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    detalhesPokemonViewModel.getPokemonRepository().inserirPokemonCapturado(pokemon);
                } else {
                    detalhesPokemonViewModel.getPokemonRepository().deletarPokemonCapturado(pokemon);
                }
            }
        });
    }


    private void voltarHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}


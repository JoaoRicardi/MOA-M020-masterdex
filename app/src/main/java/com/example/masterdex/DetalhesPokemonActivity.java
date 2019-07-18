package com.example.masterdex;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.models.Pokemon;
import com.squareup.picasso.Picasso;

public class DetalhesPokemonActivity extends AppCompatActivity {

    private ImageView botaoVoltar;
    private ImageView botaoFavorito;
    private ImageView botaoCapturado;
    private ImageView imagemPokemon;
    private TextView nomePokemon;
    private ImageView tipoPokemon;
    private TextView descricaoPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        botaoVoltar = findViewById(R.id.detalhes_pokemon_voltar);
        botaoFavorito = findViewById(R.id.detalhes_pokemon_favorito);
        botaoCapturado = findViewById(R.id.detalhes_pokemon_capturado);
        imagemPokemon = findViewById(R.id.detalhes_pokemon_image_view);
        nomePokemon = findViewById(R.id.detalhes_pokemon_nome);
        tipoPokemon = findViewById(R.id.detalhes_pokemon_tipo_image_view);
        descricaoPokemon = findViewById(R.id.detalhes_pokemon_descricao_text_view);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });
        

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");

        nomePokemon.setText(pokemon.getName());

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png").into(imagemPokemon);



    }

    private void voltarHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

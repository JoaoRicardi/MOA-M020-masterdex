package com.example.masterdex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.masterdex.adapter.AdapterPokemon;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.PokemonResposta;
import com.example.masterdex.pokeApi.PokeApi;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class PokemonsActivity extends AppCompatActivity {

    //criando as referencias
    private Retrofit retrofit;
    private TextView textNomePokemon;
    private ImageView imageFotoPokemon;
    private RecyclerView recyclerPokemons;
    private AdapterPokemon pokemonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        // ligando as coisas
        textNomePokemon = findViewById(R.id.textNomePokemon);
        imageFotoPokemon = findViewById(R.id.imageFotoPokemon);


        // configuração do recyclerview
        recyclerPokemons = findViewById(R.id.recyclerView);
        pokemonAdapter = new AdapterPokemon();
        GridLayoutManager LayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerPokemons.setLayoutManager(LayoutManager);
        recyclerPokemons.setAdapter(pokemonAdapter);


        //retrofit em ação kkk
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")// url que ira ser passada para ser consumida
                .addConverterFactory(GsonConverterFactory.create())// conversor que ira converter um json em objeto
                .build();

        receberDados();
    }


    // metodo que faz a chamada de dados
    private void receberDados() {

        final PokeApi service = retrofit.create(PokeApi.class);
        Call<PokemonResposta> pokemonRespostaCall = service.obterListaPokemon();


        pokemonRespostaCall.enqueue(new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                if (response.isSuccessful()) {
                    PokemonResposta pokemonResposta = response.body(); //colocando na variavel os dados recuperados pelo metodo @GET

                    ArrayList<Pokemon> pokemonArrayList = pokemonResposta.getResults(); //

                    pokemonAdapter.adicionarListaPokemon(pokemonArrayList);// adicionando todos os objetos num array

                } else {


                }
            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {


            }
        });

    }






    }

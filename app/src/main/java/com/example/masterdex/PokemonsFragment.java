package com.example.masterdex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class PokemonsFragment extends Fragment {

    //criando as referencias
    private Retrofit retrofit;
    private TextView textNomePokemon;
    private ImageView imageFotoPokemon;
    private RecyclerView recyclerPokemons;
    private AdapterPokemon pokemonAdapter;


    public PokemonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemons, container, false);

    // ligando as coisas, lembrando que tudo está sendo instanciado em uma View
    textNomePokemon = view.findViewById(R.id.textNomePokemon);
    imageFotoPokemon = view.findViewById(R.id.imageFotoPokemon);

    recyclerPokemons = view.findViewById(R.id.recyclerView);
    pokemonAdapter = new AdapterPokemon();
    GridLayoutManager LayoutManager = new GridLayoutManager(getContext(),3);
    recyclerPokemons.setLayoutManager(LayoutManager);
    recyclerPokemons.setAdapter(pokemonAdapter);

    // retrofit em ação
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")// url que ira ser passada para ser consumida
                .addConverterFactory(GsonConverterFactory.create())// conversor que ira converter um json em objeto
                .build();

        receberDados();

        return view;

    }

    private void receberDados(){

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

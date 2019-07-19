package com.example.masterdex.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.interfaces.PokemonListener;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class AdapterPokemon  extends RecyclerView.Adapter<AdapterPokemon.MyViewHolder> {


        private ArrayList<Pokemon> pokemonsDados;
        private PokemonListener pokemonListener;

        public AdapterPokemon (PokemonListener pokemonListener, ArrayList<Pokemon> pokemonsDados){ // construtor
            this.pokemonsDados = pokemonsDados;
            this.pokemonListener = pokemonListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula,viewGroup,false);// fazendo a conversao de um xlm para uma view

            return new MyViewHolder(view); // retornando uma nova view holder passando a view que era um xml
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

            final Pokemon pokemon = pokemonsDados.get(i);
            myViewHolder.textNomePokemon.setText(pokemon.getName());


            Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
                    .into(myViewHolder.imageFotoPokemon);

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemonListener.onPokemonClicado(pokemon);
                }
            });

        }

        @Override
        public int getItemCount() {
            return pokemonsDados.size();
        }

        public void adicionarListaPokemon(ArrayList<Pokemon> pokemonArrayList) {

            pokemonsDados.addAll(pokemonArrayList);
            notifyDataSetChanged();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView textNomePokemon;
            public ImageView imageFotoPokemon;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
                imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);
            }
        }
    }





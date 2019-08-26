package com.example.masterdex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.PokemonRanking;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerfilPopulares extends RecyclerView.Adapter<AdapterPerfilPopulares.ViewHolder> {

    private List<PokemonRanking> populares;

    public AdapterPerfilPopulares(List<PokemonRanking> populares ) {


        this.populares = new ArrayList<>(populares);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_favoritos_votacao,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final PokemonRanking pokemon = populares.get(position);

        String pok = pokemon.getNome();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));




        viewHolder.textNomePokemon.setText(pok + ": "+pokemon.getQtdVotos()+ " votos");


        //Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
                //.into(viewHolder.imageFotoPokemon);
    }

    @Override
    public int getItemCount() {
        return populares.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNomePokemon;
      //  private ImageView imageFotoPokemon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.nome_pokemon_votado);
            //imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);

        }
    }

    public void atualizarPopularesPerfil(List<PokemonRanking> favoritosListPokemon){
        populares = favoritosListPokemon;


        notifyDataSetChanged();
    }
}
package com.example.masterdex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.models.RegiaoPokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonRegiaoAdapter extends RecyclerView.Adapter<PokemonRegiaoAdapter.ViewHolder> {

    private List<RegiaoPokemon> regiaoPokemonList = new ArrayList<>();

    public void atualizarPokemon(List<RegiaoPokemon> regiaoPokemonList) {
        this.regiaoPokemonList = regiaoPokemonList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemons_celula, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RegiaoPokemon regiaoPokemon = regiaoPokemonList.get(position);
        holder.bind(regiaoPokemon);

    }

    @Override
    public int getItemCount() {
        return regiaoPokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagemPokemonImageView;
        private TextView nomePokemonTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePokemonTextView = itemView.findViewById(R.id.textNomePokemon);
            imagemPokemonImageView = itemView.findViewById(R.id.imageFotoPokemon);
        }

        public void bind(RegiaoPokemon regiaoPokemon) {

            nomePokemonTextView.setText(regiaoPokemon.getPokemon().getName());
            Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                    + regiaoPokemon.getEntryNumber() + ".png")
                    .into(imagemPokemonImageView);
        }
    }
}

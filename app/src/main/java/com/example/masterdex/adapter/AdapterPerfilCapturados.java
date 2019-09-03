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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerfilCapturados extends RecyclerView.Adapter<AdapterPerfilCapturados.ViewHolder> {


    private List<Pokemon> capturados;
    private Context context;


    public AdapterPerfilCapturados(List<Pokemon> capturados, Context c ){

        this.context = c;
        this.capturados = new ArrayList<>(capturados);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula_favoritos, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Pokemon pokemon = capturados.get(position);

        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));

        viewHolder.textNomePokemon.setText(pok);


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .into(viewHolder.imageFotoPokemon);

    }

    @Override
    public int getItemCount() {
        return capturados.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);

        }
    }



}
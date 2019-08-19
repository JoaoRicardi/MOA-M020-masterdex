package com.example.masterdex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerfilCapturados extends RecyclerView.Adapter<AdapterPerfilCapturados.ViewHolder> implements Filterable {

    private List<Pokemon> pokemonFilteredList;
    private List<Pokemon> pokemondListFull;

    public AdapterPerfilCapturados(List<Pokemon> pokemondListFull){

        this.pokemonFilteredList = pokemonFilteredList;

        this.pokemonFilteredList = new ArrayList<>(pokemondListFull);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Pokemon pokemon = pokemonFilteredList.get(position);

        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));

        viewHolder.textNomePokemon.setText(pok);


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
                .into(viewHolder.imageFotoPokemon);

    }

    @Override
    public int getItemCount() {
        return pokemonFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return pokemonFilter;
    }

    private Filter pokemonFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pokemon> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(pokemondListFull);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon pokemon : pokemondListFull) {
                    if (pokemon.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(pokemon);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            pokemonFilteredList.clear();
            pokemonFilteredList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);

        }
    }

    public void atualizarCapturadosPerfil(List<Pokemon> favoritosListPokemon){
        pokemondListFull = favoritosListPokemon;
        pokemonFilteredList.addAll(pokemondListFull);
        notifyDataSetChanged();
    }

}

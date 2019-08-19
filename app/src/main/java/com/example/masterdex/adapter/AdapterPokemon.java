package com.example.masterdex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.masterdex.interfaces.PokemonListener;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> implements Filterable {

    private List<Pokemon> pokemonsListFull;

    private PokemonListener pokemonListener;
    private List<Pokemon> filteredList;


    public AdapterPokemon(PokemonListener pokemonListener, ArrayList<Pokemon> pokemonsList) { // construtor
        this.pokemonsListFull = pokemonsList;
        this.pokemonListener = pokemonListener;
        this.filteredList = new ArrayList<>(pokemonsListFull);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula, viewGroup, false);// fazendo a conversao de um xlm para uma view

        return new ViewHolder(view); // retornando uma nova view holder passando a view que era um xml
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Pokemon pokemon = filteredList.get(position);

        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));

        viewHolder.textNomePokemon.setText(pok);


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .into(viewHolder.imageFotoPokemon);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemonListener.onPokemonClicado(pokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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

                filteredList.addAll(pokemonsListFull);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon pokemon : pokemonsListFull) {
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
            filteredList.clear();
            filteredList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);
        }
    }


    public void atualizarListaPokemons(List<Pokemon> listaPokemon) {

        pokemonsListFull = listaPokemon;
        filteredList.addAll(pokemonsListFull);
        notifyDataSetChanged();
    }

    public void sortNameByAsc() {
        Comparator<Pokemon> comparator = new Comparator<Pokemon>() {

            @Override
            public int compare(Pokemon object1, Pokemon object2) {
                return object1.getName().toLowerCase().compareToIgnoreCase(object2.getName());
            }
        };
        Collections.sort(pokemonsListFull, comparator);
        notifyDataSetChanged();

    }

    public void sortNameByNum() {
        Comparator<Pokemon> comparator = new Comparator<Pokemon>() {

            @Override
            public int compare(Pokemon object1, Pokemon object2) {
                return object2.getName().toLowerCase().compareToIgnoreCase(object1.getName());
            }
        };
        Collections.sort(pokemonsListFull, comparator);
        notifyDataSetChanged();

    }


}





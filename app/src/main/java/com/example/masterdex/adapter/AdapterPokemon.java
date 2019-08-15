package com.example.masterdex.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.interfaces.PokemonListener;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.R;
import com.example.masterdex.models.PokemonApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class AdapterPokemon  extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> implements Filterable  {


    private List<PokemonApi> pokemonsListFull;
    private PokemonListener pokemonListener;


    public AdapterPokemon (PokemonListener pokemonListener, ArrayList<PokemonApi> pokemonsList){ // construtor
        this.pokemonsListFull = pokemonsList;
        this.pokemonListener = pokemonListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula,viewGroup,false);// fazendo a conversao de um xlm para uma view

        return new ViewHolder(view); // retornando uma nova view holder passando a view que era um xml
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        PokemonApi pokemon = pokemonsListFull.get(position);
        viewHolder.bind(pokemon);



    }

    @Override
    public int getItemCount() {
        return pokemonsListFull.size();
    }

    @Override
    public Filter getFilter() {
        return pokemonFilter;
    }

    private Filter pokemonFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PokemonApi> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredList.addAll(pokemonsListFull);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PokemonApi pokemon : pokemonsListFull) {
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
            pokemonsListFull.clear();
            pokemonsListFull.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.image_pokemon_recycler_home);
        }

        public void bind(PokemonApi pokemon) {
            textNomePokemon.setText(pokemon.getName());
         //   Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(imageFotoPokemon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemonListener.onPokemonClicado(pokemon);

                }
            });


        }
    }


    public void adicionarListaPokemon(ArrayList<PokemonApi> pokemonArrayList) {

        pokemonsListFull.addAll(pokemonArrayList);
        notifyDataSetChanged();
    }


    public void atualizarListaPokemons(List<PokemonApi> listaFiltrada) {

        pokemonsListFull = new ArrayList<>();
        pokemonsListFull.addAll(listaFiltrada);
        notifyDataSetChanged();
    }
}





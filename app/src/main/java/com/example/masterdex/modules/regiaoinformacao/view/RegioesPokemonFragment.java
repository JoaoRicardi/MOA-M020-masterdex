package com.example.masterdex.modules.regiaoinformacao.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;
import com.example.masterdex.adapter.PokemonRegiaoAdapter;
import com.example.masterdex.models.Regiao;
import com.example.masterdex.modules.regiaoinformacao.viewmodel.RegioesPokemonViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegioesPokemonFragment extends Fragment {

    public static final int LIMIT = 20;
    private static final String SORT = "id:asc";
    private RecyclerView regiaoPokemonRecyclerView;
    private PokemonRegiaoAdapter pokemonRegiaoAdapter;


    public RegioesPokemonFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regioes_pokemon, container, false);

        regiaoPokemonRecyclerView = view.findViewById(R.id.regioes_pokemon_reycler_view_id);
        pokemonRegiaoAdapter = new PokemonRegiaoAdapter();

        regiaoPokemonRecyclerView.setAdapter(pokemonRegiaoAdapter);
        regiaoPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();

        RegioesPokemonViewModel regioesPokemonViewModel = ViewModelProviders.of(this).get(RegioesPokemonViewModel.class);

        String nomeRegiao = bundle.getString("REGIAO");

        regioesPokemonViewModel.atualizarPokemon(nomeRegiao.toLowerCase(),LIMIT, SORT);

        regioesPokemonViewModel.getRegiaoPokemonLiveData()
                .observe(this, regiaoPokemonsList -> {



                });

        return view;
    }

}

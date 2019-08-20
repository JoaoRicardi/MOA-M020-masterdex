package com.example.masterdex.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.adapter.AdapterPerfilPopulares;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.PokemonViewModel;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class PopuladoresPerfilFragment extends Fragment {

    private RecyclerView popularesRecyclerView;
    private AdapterPerfilPopulares popularesAdapter;
    private static final int LIMIT = 20;
    private int offset = 0;

    public PopuladoresPerfilFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_populares_perfil,container,false);

        ArrayList<Pokemon> pokemonPopularesList = new ArrayList<>();

        popularesRecyclerView = view.findViewById(R.id.populares_perfil_recyclerview_id);

        popularesAdapter = new AdapterPerfilPopulares(pokemonPopularesList);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        popularesRecyclerView.setLayoutManager(layoutManager);
        popularesRecyclerView.setAdapter(popularesAdapter);

        atualizarPopulares();



        return view;
    }

    private void atualizarPopulares() {

        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.atualizarPokemon(LIMIT, offset);
        pokemonViewModel.getPokemonLiveData()
                .observe(this, pokemon -> popularesAdapter.atualizarPopularesPerfil(pokemon));
    }
}
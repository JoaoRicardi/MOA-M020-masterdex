package com.example.masterdex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.adapter.AdapterPerfilFavoritos;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.PokemonViewModel;

import java.util.ArrayList;


public class FavoritosPerfilFragment extends Fragment {

    private RecyclerView favoritosRecyclerView;
    private AdapterPerfilFavoritos favoritosAdapter;
    private static final int LIMIT = 20;
    private int offset = 0;

    public FavoritosPerfilFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favoritos_perfil,container,false);

        ArrayList<Pokemon> pokemonFavoritosList = new ArrayList<>();

        favoritosRecyclerView = view.findViewById(R.id.favoritos_perfil_recyclerview_id);

        favoritosAdapter = new AdapterPerfilFavoritos(pokemonFavoritosList);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        favoritosRecyclerView.setLayoutManager(layoutManager);
        favoritosRecyclerView.setAdapter(favoritosAdapter);

        atualizarFavoritos();



        return view;
    }

    private void atualizarFavoritos() {

        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.atualizarPokemon(LIMIT, offset);
        pokemonViewModel.getPokemonLiveData()
                .observe(this, pokemon -> favoritosAdapter.atualizarFavoritosPerfil(pokemon));
    }
}

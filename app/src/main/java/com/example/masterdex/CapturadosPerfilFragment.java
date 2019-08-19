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

import com.example.masterdex.adapter.AdapterPerfilCapturados;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.PokemonViewModel;

import java.util.ArrayList;

public class CapturadosPerfilFragment extends Fragment {

    private RecyclerView capturadosRecyclerView;
    private AdapterPerfilCapturados capturadosAdapter;
    private static final int LIMIT = 20;
    private int offset = 0;

    public CapturadosPerfilFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capturados_perfil,container,false);

        ArrayList<Pokemon> pokemonCapturadoList = new ArrayList<>();

        capturadosRecyclerView = view.findViewById(R.id.capturados_perfil_recyclerview_id);

        capturadosAdapter = new AdapterPerfilCapturados(pokemonCapturadoList);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        capturadosRecyclerView.setLayoutManager(layoutManager);
        capturadosRecyclerView.setAdapter(capturadosAdapter);

        atualizarCapturados();



        return view;
    }

    private void atualizarCapturados() {
        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.atualizarPokemon(LIMIT, offset);
        pokemonViewModel.getPokemonLiveData()
                .observe(this, pokemon -> capturadosAdapter.atualizarCapturadosPerfil(pokemon));
    }
}

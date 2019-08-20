package com.example.masterdex.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;
import com.example.masterdex.adapter.AdapterHabilidades;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.SlotHabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HabilidadesFragment extends Fragment {


    private AdapterHabilidades adapterHabilidades;
    private RecyclerView recyclerView;

    public HabilidadesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_habilidades, container, false);

        recyclerView = view.findViewById(R.id.habilidades_recycler_view);

        Bundle bundle = getArguments();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");

        List<SlotHabilidade>  slotHabilidadeList = pokemon.getMoves();

        adapterHabilidades = new AdapterHabilidades(slotHabilidadeList, pokemon);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapterHabilidades);

        return view;
    }

}

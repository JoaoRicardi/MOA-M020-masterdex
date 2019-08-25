package com.example.masterdex.modules.regiaoinformacao.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;
import com.example.masterdex.adapter.LocalidadesAdapter;
import com.example.masterdex.adapter.RegioesAdapter;
import com.example.masterdex.models.Cidade;
import com.example.masterdex.models.Regiao;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CidadesRegioesFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Cidade> listaDeCidades;
    private Regiao regiao;

    public CidadesRegioesFragment(Regiao regiao) {
        this.regiao = regiao;
    }

    public CidadesRegioesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cidades_regioes, container, false);

        listaDeCidades = regiao.getCidadeList();

        LocalidadesAdapter localidadesAdapter = new LocalidadesAdapter(listaDeCidades);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.regioes_locais_recycler_view);

        recyclerView.setAdapter(localidadesAdapter);

        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}

package com.example.masterdex.modules.regiaoinformacao.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegioesPokemonFragment extends Fragment {


    public RegioesPokemonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regioes_pokemon, container, false);
    }

}

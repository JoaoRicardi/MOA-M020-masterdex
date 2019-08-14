package com.example.masterdex.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HabilidadesFragment extends Fragment {


    public HabilidadesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habilidades, container, false);
    }

}

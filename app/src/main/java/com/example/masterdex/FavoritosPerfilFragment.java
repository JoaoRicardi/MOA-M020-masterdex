package com.example.masterdex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.adapter.AdapterPerfilFavoritos;


public class FavoritosPerfilFragment extends Fragment {

    private RecyclerView favoritosRecyclerView;
    private AdapterPerfilFavoritos favoritosAdapter;
    private static final int LIMIT = 20;
    private int offset = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favoritos_perfil,container,false);
        return view;




    }
}

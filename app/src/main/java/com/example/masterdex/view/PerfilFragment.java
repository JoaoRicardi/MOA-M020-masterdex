package com.example.masterdex.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.masterdex.CapturadosPerfilFragment;
import com.example.masterdex.FavoritosPerfilFragment;
import com.example.masterdex.PopuladoresPerfilFragment;
import com.example.masterdex.R;
import com.example.masterdex.adapter.PerfilViewPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class PerfilFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    Button buttonOpcoesPerfil;

    private TabLayout tabLayoutPerfil;
    private ViewPager viewPagerPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        tabLayoutPerfil = view.findViewById(R.id.tablayoutperfil_id);
        viewPagerPerfil = view.findViewById(R.id.viewpagerperfil_id);

        PerfilViewPagerAdapter perfilViewPagerAdapter = new PerfilViewPagerAdapter(getChildFragmentManager());
        perfilViewPagerAdapter.AddFragment(new FavoritosPerfilFragment(),"Favoritos");
        perfilViewPagerAdapter.AddFragment(new CapturadosPerfilFragment(),"Capturados");
        perfilViewPagerAdapter.AddFragment(new PopuladoresPerfilFragment(),"Populares");

        viewPagerPerfil.setAdapter(perfilViewPagerAdapter);
        tabLayoutPerfil.setupWithViewPager(viewPagerPerfil);

        buttonOpcoesPerfil = view.findViewById(R.id.button_opcoes_perfil);

        buttonOpcoesPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(), buttonOpcoesPerfil);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(PerfilFragment.this);


            }
        });

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.item_editar_perfil:
                Intent intent = new Intent(getContext(), EditarPerfilActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_sair:
                Intent intent2 = new Intent(getContext(), LoginActivity.class);
                startActivity(intent2);
                return true;
            default:
                return false;
        }
    }
}

package com.example.masterdex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;


public class PerfilFragment extends Fragment {

    Button buttonOpcoesPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        buttonOpcoesPerfil = view.findViewById(R.id.button_opcoes_perfil);
        buttonOpcoesPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(), buttonOpcoesPerfil);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();


            }
        });

        return view;
    }

}

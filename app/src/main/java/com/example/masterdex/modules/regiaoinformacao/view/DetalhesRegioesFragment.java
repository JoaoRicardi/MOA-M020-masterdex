package com.example.masterdex.modules.regiaoinformacao.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masterdex.R;
import com.example.masterdex.models.Regiao;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalhesRegioesFragment extends Fragment {

    private Regiao regiao;
    private TextView detalheRegiaoTextView;


    public DetalhesRegioesFragment(Regiao regiao) {
        this.regiao = regiao;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_regioes, container, false);

        detalheRegiaoTextView = view.findViewById(R.id.texto_informacoes_text_view);

        detalheRegiaoTextView.setText(regiao.getDescricaoRegiao());

        return view;
    }

}

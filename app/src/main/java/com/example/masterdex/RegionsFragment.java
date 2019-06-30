package com.example.masterdex;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.adapter.RegioesAdapter;
import com.example.masterdex.interfaces.FragmentActionListener;
import com.example.masterdex.interfaces.RegioesListener;
import com.example.masterdex.models.Regiao;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegionsFragment extends Fragment {

    private RecyclerView recyclerView;

    public RegionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regions, container, false);

        recyclerView = view.findViewById(R.id.regioes_recycler_view);

        RegioesAdapter regioesAdapter = new RegioesAdapter(getListaRegioes());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setAdapter(regioesAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private List<Regiao> getListaRegioes() {

        List<Regiao> listaRegioes = new ArrayList<>();

        Regiao kanto = new Regiao("Kanto");
        kanto.setImagemRegiao("https://1.bp.blogspot.com/-lptR-cLgKUY/W-St09lnVRI/AAAAAAAADwU/4tzbeNhJs94Suug-Fwtmm_8SSTYNkCP1QCLcBGAs/s1600/kanto%2Bmap%2Bletsgo.png");
        listaRegioes.add(kanto);

        Regiao Johto = new Regiao("Johto");
        Johto.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/3/3c/HGSSArt_Johto.png/revision/latest?cb=20180712140112");
        listaRegioes.add(Johto);

        Regiao Hoenn = new Regiao("Hoenn");
        Hoenn.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/b4/ORASArt_Hoenn.png/revision/latest?cb=20170910174759");
        listaRegioes.add(Hoenn);

        Regiao Sinnoh = new Regiao("Sinnoh");
        Sinnoh.setImagemRegiao("http://1.bp.blogspot.com/_H_L3jjiClbI/SbSCBuv46QI/AAAAAAAAANc/Ikj8Xdlb4Ug/s400/mapa-sinnoh.jpg");
        listaRegioes.add(Sinnoh);

        Regiao Unova = new Regiao("Unova");
        Unova.setImagemRegiao("https://2.bp.blogspot.com/-UB-gSYlvXDM/U8YDE-Ce4HI/AAAAAAAABlI/VbI6I_BpPek/s1600/Unova+B2W2.png");
        listaRegioes.add(Unova);

        Regiao Kalos = new Regiao("Kalos");
        Kalos.setImagemRegiao("https://vignette.wikia.nocookie.net/pokepediabr/images/a/a0/Mapa_de_Kalos.png/revision/latest?cb=20171113011650&path-prefix=pt-br");
        listaRegioes.add(Kalos);

        Regiao SeviiSlands = new Regiao("Sevii Slands");
        SeviiSlands.setImagemRegiao("https://cdn.bulbagarden.net/upload/thumb/3/32/Sevii_Islands.png/250px-Sevii_Islands.png");
        listaRegioes.add(SeviiSlands);

        Regiao OrangeIslands = new Regiao("Orange Islands");
        OrangeIslands.setImagemRegiao("https://vignette.wikia.nocookie.net/pokemon/images/9/9d/Orange_Islands.jpg/revision/latest?cb=20110306222058");
        listaRegioes.add(OrangeIslands);

        Regiao Almia = new Regiao("Almia");
        Almia.setImagemRegiao("https://cdn.bulbagarden.net/upload/f/f4/Almia.png");
        listaRegioes.add(Almia);

        Regiao Oblivia = new Regiao("Oblivia");
        Oblivia.setImagemRegiao("http://1.bp.blogspot.com/_-oa4g27SpCo/S5rr5MqbLoI/AAAAAAAACI4/N7OwpmV9KyI/s320/2.jpg");
        listaRegioes.add(Oblivia);

        Regiao Fiore = new Regiao("Fiore");
        Fiore.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/bb/PokeWiki_Fiore.png/revision/latest?cb=20170901164006");
        listaRegioes.add(Fiore);

        Regiao Alola = new Regiao("Alola");
        Alola.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/6/69/USUMArt_Alola.png/revision/latest?cb=20170819020743");
        listaRegioes.add(Alola);

        return listaRegioes;
    }
}

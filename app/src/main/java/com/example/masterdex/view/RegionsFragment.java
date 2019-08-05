package com.example.masterdex.view;



import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdex.R;
import com.example.masterdex.adapter.RegioesAdapter;

import com.example.masterdex.interfaces.RegioesListener;
import com.example.masterdex.models.Regiao;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegionsFragment extends Fragment implements RegioesListener{

    private RecyclerView recyclerView;

    public RegionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regions, container, false);

        recyclerView = view.findViewById(R.id.regioes_recycler_view);

        RegioesAdapter regioesAdapter = new RegioesAdapter(getListaRegioes(), this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setAdapter(regioesAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private List<Regiao> getListaRegioes() {

        List<Regiao> listaRegioes = new ArrayList<>();

        Regiao kanto = new Regiao("Kanto");
        kanto.setImagemRegiao("https://1.bp.blogspot.com/-lptR-cLgKUY/W-St09lnVRI/AAAAAAAADwU/4tzbeNhJs94Suug-Fwtmm_8SSTYNkCP1QCLcBGAs/s1600/kanto%2Bmap%2Bletsgo.png");
        kanto.setDescricaoRegiao(getString(R.string.regiao_info_kanto));
        listaRegioes.add(kanto);

        Regiao Johto = new Regiao("Johto");
        Johto.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/3/3c/HGSSArt_Johto.png/revision/latest?cb=20180712140112");
        Johto.setDescricaoRegiao(getString(R.string.regiao_info_johto));
        listaRegioes.add(Johto);

        Regiao Hoenn = new Regiao("Hoenn");
        Hoenn.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/b4/ORASArt_Hoenn.png/revision/latest?cb=20170910174759");
        Hoenn.setDescricaoRegiao(getString(R.string.regiao_info_hoenn));
        listaRegioes.add(Hoenn);

        Regiao Sinnoh = new Regiao("Sinnoh");
        Sinnoh.setImagemRegiao("http://1.bp.blogspot.com/_H_L3jjiClbI/SbSCBuv46QI/AAAAAAAAANc/Ikj8Xdlb4Ug/s400/mapa-sinnoh.jpg");
        Sinnoh.setDescricaoRegiao(getString(R.string.regiao_info_sinnoh));
        listaRegioes.add(Sinnoh);

        Regiao Unova = new Regiao("Unova");
        Unova.setImagemRegiao("https://2.bp.blogspot.com/-UB-gSYlvXDM/U8YDE-Ce4HI/AAAAAAAABlI/VbI6I_BpPek/s1600/Unova+B2W2.png");
        Unova.setDescricaoRegiao(getString(R.string.regiao_info_unova));
        listaRegioes.add(Unova);

        Regiao Kalos = new Regiao("Kalos");
        Kalos.setImagemRegiao("https://vignette.wikia.nocookie.net/pokepediabr/images/a/a0/Mapa_de_Kalos.png/revision/latest?cb=20171113011650&path-prefix=pt-br");
        Kalos.setDescricaoRegiao(getString(R.string.regiao_info_kalos));
        listaRegioes.add(Kalos);

        Regiao SeviiSlands = new Regiao("Sevii Slands");
        SeviiSlands.setImagemRegiao("https://cdn.bulbagarden.net/upload/thumb/3/32/Sevii_Islands.png/250px-Sevii_Islands.png");
        SeviiSlands.setDescricaoRegiao(getString(R.string.regiao_info_sevii_islands));
        listaRegioes.add(SeviiSlands);

        Regiao OrangeIslands = new Regiao("Orange Islands");
        OrangeIslands.setImagemRegiao("https://vignette.wikia.nocookie.net/pokemon/images/9/9d/Orange_Islands.jpg/revision/latest?cb=20110306222058");
        OrangeIslands.setDescricaoRegiao(getString(R.string.regiao_info_orange_islands));
        listaRegioes.add(OrangeIslands);

        Regiao Almia = new Regiao("Almia");
        Almia.setImagemRegiao("https://cdn.bulbagarden.net/upload/f/f4/Almia.png");
        Almia.setDescricaoRegiao(getString(R.string.regiao_info_almia));
        listaRegioes.add(Almia);

        Regiao Oblivia = new Regiao("Oblivia");
        Oblivia.setImagemRegiao("http://1.bp.blogspot.com/_-oa4g27SpCo/S5rr5MqbLoI/AAAAAAAACI4/N7OwpmV9KyI/s320/2.jpg");
        Oblivia.setDescricaoRegiao(getString(R.string.regiao_info_oblivia));
        listaRegioes.add(Oblivia);

        Regiao Fiore = new Regiao("Fiore");
        Fiore.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/bb/PokeWiki_Fiore.png/revision/latest?cb=20170901164006");
        Fiore.setDescricaoRegiao(getString(R.string.regiao_info_fiore));
        listaRegioes.add(Fiore);

        Regiao Alola = new Regiao("Alola");
        Alola.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/6/69/USUMArt_Alola.png/revision/latest?cb=20170819020743");
        Alola.setDescricaoRegiao(getString(R.string.regiao_info_alola));
        listaRegioes.add(Alola);

        return listaRegioes;
    }

    @Override
    public void onRegiaoClicada(Regiao regiao) {
        Intent intent = new Intent(getContext(), RegioesInformacoesActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("REGIAO", regiao);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}

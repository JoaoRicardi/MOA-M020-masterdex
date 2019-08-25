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
import com.example.masterdex.models.Cidade;
import com.example.masterdex.models.Regiao;
import com.example.masterdex.modules.regiaoinformacao.view.RegioesInformacoesActivity;

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

        Cidade cidade1_kanto = new Cidade();
        cidade1_kanto.setNome("Pallet Town");

        Cidade cidade2_kanto = new Cidade();
        cidade2_kanto.setNome("Veridian City");

        Cidade cidade3_kanto = new Cidade();
        cidade3_kanto.setNome("Pewter City");

        Cidade cidade4_kanto = new Cidade();
        cidade4_kanto.setNome("Cerulean City");

        Cidade cidade5_kanto = new Cidade();
        cidade5_kanto.setNome("Vermillion City");

        Cidade cidade6_kanto = new Cidade();
        cidade6_kanto.setNome("Lavender Town");

        Cidade cidade7_kanto = new Cidade();
        cidade7_kanto.setNome("Celadon City");

        Cidade cidade8_kanto = new Cidade();
        cidade8_kanto.setNome("Saffron City");

        Cidade cidade9_kanto = new Cidade();
        cidade9_kanto.setNome("Fuchsia City ");

        Cidade cidade10_kanto = new Cidade();
        cidade10_kanto.setNome("Cinnabar Island");

        kanto.getCidadeList().add(cidade1_kanto);
        kanto.getCidadeList().add(cidade2_kanto);
        kanto.getCidadeList().add(cidade3_kanto);
        kanto.getCidadeList().add(cidade4_kanto);
        kanto.getCidadeList().add(cidade5_kanto);
        kanto.getCidadeList().add(cidade6_kanto);
        kanto.getCidadeList().add(cidade7_kanto);
        kanto.getCidadeList().add(cidade8_kanto);
        kanto.getCidadeList().add(cidade9_kanto);
        kanto.getCidadeList().add(cidade10_kanto);

        listaRegioes.add(kanto);

        Regiao Johto = new Regiao("Johto");
        Johto.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/3/3c/HGSSArt_Johto.png/revision/latest?cb=20180712140112");
        Johto.setDescricaoRegiao(getString(R.string.regiao_info_johto));

        Cidade cidade1_johto = new Cidade();
        cidade1_johto.setNome("New Bark Town");

        Cidade cidade2_johto = new Cidade();
        cidade2_johto.setNome("Cherrygrove City");

        Cidade cidade3_johto = new Cidade();
        cidade3_johto.setNome("Violet City");

        Cidade cidadee4_johto = new Cidade();
        cidadee4_johto.setNome("Azalea Town");

        Cidade cidade5_johto = new Cidade();
        cidade5_johto.setNome("Goldenrod City");

        Cidade cidade6_johto = new Cidade();
        cidade6_johto.setNome("Ecruteak City");

        Cidade cidade7_johto = new Cidade();
        cidade7_johto.setNome("Olivine City");

        Cidade cidade8_johto = new Cidade();
        cidade8_johto.setNome("Cianwood City");

        Cidade cidade9_johto = new Cidade();
        cidade9_johto.setNome("Mahogany Town");

        Cidade cidade10_johto = new Cidade();
        cidade10_johto.setNome("Blackthorn City");


        Johto.getCidadeList().add(cidade1_johto);
        Johto.getCidadeList().add(cidade2_johto);
        Johto.getCidadeList().add(cidade3_johto);
        Johto.getCidadeList().add(cidadee4_johto);
        Johto.getCidadeList().add(cidade5_johto);
        Johto.getCidadeList().add(cidade6_johto);
        Johto.getCidadeList().add(cidade7_johto);
        Johto.getCidadeList().add(cidade8_johto);
        Johto.getCidadeList().add(cidade9_johto);
        Johto.getCidadeList().add(cidade10_johto);

        listaRegioes.add(Johto);

        Regiao Hoenn = new Regiao("Hoenn");
        Hoenn.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/b4/ORASArt_Hoenn.png/revision/latest?cb=20170910174759");
        Hoenn.setDescricaoRegiao(getString(R.string.regiao_info_hoenn));

        Cidade cidade1_hoenn = new Cidade();
        cidade1_hoenn.setNome("Littleroot Town");

        Cidade cidade2_hoenn = new Cidade();
        cidade2_hoenn.setNome("Petalburg City");

        Cidade cidade3_hoenn = new Cidade();
        cidade3_hoenn.setNome("Rustboro City");

        Cidade cidade4_hoenn = new Cidade();
        cidade4_hoenn.setNome("Dewford Town");

        Cidade cidade5_hoenn = new Cidade();
        cidade5_hoenn.setNome("Slateport City;");

        Cidade cidade6_hoenn = new Cidade();
        cidade6_hoenn.setNome("Mauville City");

        Cidade cidade7_hoenn = new Cidade();
        cidade7_hoenn.setNome("Vendanturf Town");

        Cidade cidade8_hoenn = new Cidade();
        cidade8_hoenn.setNome("Lavaridge Town");

        Cidade cidade9_hoenn = new Cidade();
        cidade9_hoenn.setNome("Fortree City");

        Cidade cidade10_hoenn = new Cidade();
        cidade10_hoenn.setNome("Lilycove City");

        Cidade cidade11_hoenn = new Cidade();
        cidade11_hoenn.setNome("Mossdeep City");

        Cidade cidade12_hoenn = new Cidade();
        cidade12_hoenn.setNome("Sootopolis City");

        Cidade cidade13_hoenn = new Cidade();
        cidade13_hoenn.setNome("Pacifidlog Town");

        Cidade cidade14_hoenn = new Cidade();
        cidade14_hoenn.setNome("Ever Grande City");

        Cidade cidade15_hoenn = new Cidade();
        cidade15_hoenn.setNome("Oldale Town");



        Hoenn.getCidadeList().add(cidade1_hoenn);
        Hoenn.getCidadeList().add(cidade2_hoenn);
        Hoenn.getCidadeList().add(cidade3_hoenn);
        Hoenn.getCidadeList().add(cidade4_hoenn);
        Hoenn.getCidadeList().add(cidade5_hoenn);
        Hoenn.getCidadeList().add(cidade6_hoenn);
        Hoenn.getCidadeList().add(cidade7_hoenn);
        Hoenn.getCidadeList().add(cidade8_hoenn);
        Hoenn.getCidadeList().add(cidade9_hoenn);
        Hoenn.getCidadeList().add(cidade10_hoenn);
        Hoenn.getCidadeList().add(cidade11_hoenn);
        Hoenn.getCidadeList().add(cidade12_hoenn);
        Hoenn.getCidadeList().add(cidade13_hoenn);
        Hoenn.getCidadeList().add(cidade14_hoenn);
        Hoenn.getCidadeList().add(cidade15_hoenn);
        listaRegioes.add(Hoenn);

        Regiao Sinnoh = new Regiao("Sinnoh");
        Sinnoh.setImagemRegiao("http://1.bp.blogspot.com/_H_L3jjiClbI/SbSCBuv46QI/AAAAAAAAANc/Ikj8Xdlb4Ug/s400/mapa-sinnoh.jpg");
        Sinnoh.setDescricaoRegiao(getString(R.string.regiao_info_sinnoh));

        Cidade cidade1_sinnoh = new Cidade();
        cidade1_sinnoh.setNome("Oreburgh City");

        Cidade cidade2_sinnoh = new Cidade();
        cidade2_sinnoh.setNome("Eterna City");

        Cidade cidade3_sinnoh = new Cidade();
        cidade3_sinnoh.setNome("Canalave City");

        Cidade cidade4_sinnoh = new Cidade();
        cidade4_sinnoh.setNome("Jubilife City");

        Cidade cidade5_sinnoh = new Cidade();
        cidade5_sinnoh.setNome("Hearthome City");

        Cidade cidade6_sinnoh = new Cidade();
        cidade6_sinnoh.setNome("Veilstone City");

        Cidade cidade7_sinnoh = new Cidade();
        cidade7_sinnoh.setNome("Pastoria City");

        Cidade cidade8_sinnoh = new Cidade();
        cidade8_sinnoh.setNome("Sunyshore City");

        Cidade cidade9_sinnoh = new Cidade();
        cidade9_sinnoh.setNome("Snowpoint City");

        Sinnoh.getCidadeList().add(cidade1_sinnoh);
        Sinnoh.getCidadeList().add(cidade2_sinnoh);
        Sinnoh.getCidadeList().add(cidade3_sinnoh);
        Sinnoh.getCidadeList().add(cidade4_sinnoh);
        Sinnoh.getCidadeList().add(cidade5_sinnoh);
        Sinnoh.getCidadeList().add(cidade6_sinnoh);
        Sinnoh.getCidadeList().add(cidade7_sinnoh);
        Sinnoh.getCidadeList().add(cidade8_sinnoh);
        Sinnoh.getCidadeList().add(cidade9_sinnoh);
        listaRegioes.add(Sinnoh);

        Regiao Unova = new Regiao("Unova");
        Unova.setImagemRegiao("https://2.bp.blogspot.com/-UB-gSYlvXDM/U8YDE-Ce4HI/AAAAAAAABlI/VbI6I_BpPek/s1600/Unova+B2W2.png");
        Unova.setDescricaoRegiao(getString(R.string.regiao_info_unova));

        Cidade cidade1_unova = new Cidade();
        cidade1_unova.setNome("Striaton City");

        Cidade cidade2_unova = new Cidade();
        cidade2_unova.setNome("Nacrene City");

        Cidade cidade3_unova = new Cidade();
        cidade3_unova.setNome("Castelia City");

        Cidade cidade4_unova = new Cidade();
        cidade4_unova.setNome("Nimbasa City");

        Cidade cidade5_unova = new Cidade();
        cidade5_unova.setNome("Driftveil City");

        Cidade cidade6_unova = new Cidade();
        cidade6_unova.setNome("Mistralton City");

        Cidade cidade7_unova = new Cidade();
        cidade7_unova.setNome("Icirrus City");

        Cidade cidade8_unova = new Cidade();
        cidade8_unova.setNome("Mistralton City");

        Cidade cidade9_unova = new Cidade();
        cidade9_unova.setNome("Opelucid City");

        Cidade cidade10_unova = new Cidade();
        cidade10_unova.setNome("Aspertia City");

        Cidade cidade11_unova = new Cidade();
        cidade11_unova.setNome("Virbank City");

        Cidade cidade12_unova = new Cidade();
        cidade12_unova.setNome("Humilau City");

        Unova.getCidadeList().add(cidade1_unova);
        Unova.getCidadeList().add(cidade2_unova);
        Unova.getCidadeList().add(cidade3_unova);
        Unova.getCidadeList().add(cidade4_unova);
        Unova.getCidadeList().add(cidade5_unova);
        Unova.getCidadeList().add(cidade6_unova);
        Unova.getCidadeList().add(cidade7_unova);
        Unova.getCidadeList().add(cidade8_unova);
        Unova.getCidadeList().add(cidade9_unova);
        Unova.getCidadeList().add(cidade10_unova);
        Unova.getCidadeList().add(cidade11_unova);
        Unova.getCidadeList().add(cidade12_unova);
        listaRegioes.add(Unova);

        Regiao Kalos = new Regiao("Kalos");
        Kalos.setImagemRegiao("https://vignette.wikia.nocookie.net/pokepediabr/images/a/a0/Mapa_de_Kalos.png/revision/latest?cb=20171113011650&path-prefix=pt-br");
        Kalos.setDescricaoRegiao(getString(R.string.regiao_info_kalos));

        Cidade cidade1_kalos = new Cidade();
        cidade1_kalos.setNome("Santalune City");

        Cidade cidade2_kalos = new Cidade();
        cidade2_kalos.setNome("Lumiose City ");

        Cidade cidade3_kalos = new Cidade();
        cidade3_kalos.setNome("Cyllage City");

        Cidade cidade4_kalos = new Cidade();
        cidade4_kalos.setNome("Shalour City");

        Cidade cidade5_kalos = new Cidade();
        cidade5_kalos.setNome("Coumarine Cirty");

        Cidade cidade6_kalos = new Cidade();
        cidade6_kalos.setNome("Laverre City");

        Cidade cidade7_kalos = new Cidade();
        cidade7_kalos.setNome("Anistar City");

        Cidade cidade8_kalos = new Cidade();
        cidade8_kalos.setNome("Snowbelle City");

        Cidade cidade9_kalos = new Cidade();
        cidade9_kalos.setNome("Kiloude City");

        Kalos.getCidadeList().add(cidade1_kalos);
        Kalos.getCidadeList().add(cidade2_kalos);
        Kalos.getCidadeList().add(cidade3_kalos);
        Kalos.getCidadeList().add(cidade4_kalos);
        Kalos.getCidadeList().add(cidade5_kalos);
        Kalos.getCidadeList().add(cidade6_kalos);
        Kalos.getCidadeList().add(cidade7_kalos);
        Kalos.getCidadeList().add(cidade8_kalos);
        Kalos.getCidadeList().add(cidade9_kalos);
        listaRegioes.add(Kalos);

        Regiao SeviiSlands = new Regiao("Sevii Slands");
        SeviiSlands.setImagemRegiao("https://cdn.bulbagarden.net/upload/thumb/3/32/Sevii_Islands.png/250px-Sevii_Islands.png");
        SeviiSlands.setDescricaoRegiao(getString(R.string.regiao_info_sevii_islands));

        Cidade cidade1_seviiSlands = new Cidade();
        cidade1_seviiSlands.setNome("New Bark Town");

        SeviiSlands.getCidadeList().add(cidade1_seviiSlands);
        listaRegioes.add(SeviiSlands);

        Regiao OrangeIslands = new Regiao("Orange Islands");
        OrangeIslands.setImagemRegiao("https://vignette.wikia.nocookie.net/pokemon/images/9/9d/Orange_Islands.jpg/revision/latest?cb=20110306222058");
        OrangeIslands.setDescricaoRegiao(getString(R.string.regiao_info_orange_islands));

        Cidade cidade1_orangeSlands = new Cidade();
        cidade1_orangeSlands.setNome("New Bark Town");

        OrangeIslands.getCidadeList().add(cidade1_orangeSlands);
        listaRegioes.add(OrangeIslands);

        Regiao Almia = new Regiao("Almia");
        Almia.setImagemRegiao("https://cdn.bulbagarden.net/upload/f/f4/Almia.png");
        Almia.setDescricaoRegiao(getString(R.string.regiao_info_almia));

        Cidade cidade1_almia = new Cidade();
        cidade1_almia.setNome("New Bark Town");

        Almia.getCidadeList().add(cidade1_almia);
        listaRegioes.add(Almia);

        Regiao Oblivia = new Regiao("Oblivia");
        Oblivia.setImagemRegiao("http://1.bp.blogspot.com/_-oa4g27SpCo/S5rr5MqbLoI/AAAAAAAACI4/N7OwpmV9KyI/s320/2.jpg");
        Oblivia.setDescricaoRegiao(getString(R.string.regiao_info_oblivia));

        Cidade cidade1_oblivia = new Cidade();
        cidade1_oblivia.setNome("New Bark Town");

        Oblivia.getCidadeList().add(cidade1_oblivia);
        listaRegioes.add(Oblivia);

        Regiao Fiore = new Regiao("Fiore");
        Fiore.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/b/bb/PokeWiki_Fiore.png/revision/latest?cb=20170901164006");
        Fiore.setDescricaoRegiao(getString(R.string.regiao_info_fiore));

        Cidade cidade1_fiore = new Cidade();
        cidade1_fiore.setNome("Fall City");

        Cidade cidade2_fiore = new Cidade();
        cidade2_fiore.setNome("Ringtown City");

        Cidade cidade3_fiore = new Cidade();
        cidade3_fiore.setNome("Summerland City");

        Cidade cidade4_fiore = new Cidade();
        cidade4_fiore.setNome("Wintown");

        Fiore.getCidadeList().add(cidade1_fiore);
        Fiore.getCidadeList().add(cidade2_fiore);
        Fiore.getCidadeList().add(cidade3_fiore);
        Fiore.getCidadeList().add(cidade4_fiore);
        listaRegioes.add(Fiore);

        Regiao Alola = new Regiao("Alola");
        Alola.setImagemRegiao("https://vignette.wikia.nocookie.net/victoryroad/images/6/69/USUMArt_Alola.png/revision/latest?cb=20170819020743");
        Alola.setDescricaoRegiao(getString(R.string.regiao_info_alola));

        Cidade cidade1_alola = new Cidade();
        cidade1_alola.setNome("Melemele Island");

        Cidade cidade2_alola = new Cidade();
        cidade2_alola.setNome("Akala Island");

        Cidade cidade3_alola = new Cidade();
        cidade3_alola.setNome("Ula’Ula Island");

        Cidade cidade4_alola = new Cidade();
        cidade4_alola.setNome("Poni Island");

        Cidade cidade5_alola = new Cidade();
        cidade5_alola.setNome("Aether Paradise");

        Alola.getCidadeList().add(cidade1_alola);
        Alola.getCidadeList().add(cidade2_alola);
        Alola.getCidadeList().add(cidade3_alola);
        Alola.getCidadeList().add(cidade4_alola);
        Alola.getCidadeList().add(cidade5_alola);
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

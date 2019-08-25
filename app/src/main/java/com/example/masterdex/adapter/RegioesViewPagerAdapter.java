package com.example.masterdex.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.masterdex.modules.regiaoinformacao.view.CidadesRegioesFragment;
import com.example.masterdex.modules.regiaoinformacao.view.DetalhesRegioesFragment;
import com.example.masterdex.models.Regiao;
import com.example.masterdex.modules.regiaoinformacao.view.RegioesPokemonFragment;

public class RegioesViewPagerAdapter extends FragmentStatePagerAdapter {

    private Regiao regiao;

    public RegioesViewPagerAdapter(FragmentManager fm, Regiao regiao) {
        super(fm);
        this.regiao = regiao;
    }

    public RegioesViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // logica bundle
        RegioesPokemonFragment pokeFrag = new RegioesPokemonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("REGIAO", regiao.getNomeRegiao());

        pokeFrag.setArguments(bundle);

        switch (position){
            case 0: return new DetalhesRegioesFragment(regiao);
            case 1: return new CidadesRegioesFragment(regiao);
            case 2: return new RegioesPokemonFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Detalhes";
            case 1: return "Cidades";
            case 2: return "Pokemon";
            default: return null;
        }
    }

}

package com.example.masterdex;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.masterdex.modules.regioes.model.Regiao;

public class RegioesViewPagerAdapter extends FragmentStatePagerAdapter {

    private Regiao regiao;

    public RegioesViewPagerAdapter(FragmentManager fm, Regiao regiao) {
        super(fm);
        this.regiao = regiao;
    }

    public RegioesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DetalhesRegioesFragment(regiao);
            case 1: return new CidadesRegioesFragment();
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

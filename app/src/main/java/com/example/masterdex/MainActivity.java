package com.example.masterdex;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.masterdex.adapter.ViewPagerAdapter;



public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_facebook};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        tabLayout.setupWithViewPager(viewPager);




        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adicionando os Fragments
        adapter.AddFragment(new PokemonsFragment(), "Dex");
        adapter.AddFragment(new PerfilFragment(), "Perfil");
        adapter.AddFragment(new RegionsFragment(), "Regiões");


        // Adapter Setup

        viewPager.setAdapter(adapter);
    }



}

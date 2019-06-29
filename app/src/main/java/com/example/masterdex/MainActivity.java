package com.example.masterdex;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.masterdex.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_facebook};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_tab_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager = findViewById(R.id.viewpager_id);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(viewPager);

    }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.AddFragment(new PokemonsFragment(), "Dex");
            adapter.AddFragment(new PerfilFragment(), "Perfil");
            adapter.AddFragment(new RegionsFragment(), "Regiões");
            viewPager.setAdapter(adapter);
        }
}





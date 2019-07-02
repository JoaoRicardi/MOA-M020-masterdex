package com.example.masterdex;



import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.masterdex.adapter.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity{


    private BottomNavigationView bottomNavigationItemView;
    private ViewPager viewPager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager_id);

        setupViewPager(viewPager);

        viewPager.setCurrentItem(1,true);

        bottomNavigationItemView = findViewById(R.id.bottom_navigation);

        bottomNavigationItemView.getMenu().getItem(1).setChecked(true);

        bottomNavigationItemView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.perfil:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.dex:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.regioes:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return true;
                    }
                });



    }


        private void setupViewPager(ViewPager viewPager) {

            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

            adapter.AddFragment(new PerfilFragment(), "Perfil");

            adapter.AddFragment(new PokemonsFragment(), "Dex");

            adapter.AddFragment(new RegionsFragment(), "Regiões");
            viewPager.setAdapter(adapter);

        }
}





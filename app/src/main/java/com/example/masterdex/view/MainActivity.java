package com.example.masterdex.view;


import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.masterdex.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.masterdex.adapter.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity{


    private BottomNavigationView bottomNavigationItemView;
    private ViewPager viewPager;
    MenuItem prevMenuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager_id);


        setupViewPager(viewPager);

        //viewPager.setCurrentItem(1,true);

        bottomNavigationItemView = findViewById(R.id.bottom_navigation);

        //bottomNavigationItemView.getMenu().getItem(1).setChecked(true);

        bottomNavigationItemView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem itemMenu) {
                        switch (itemMenu.getItemId()) {
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationItemView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationItemView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationItemView.getMenu().getItem(position);

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        viewPager.setCurrentItem(1, true);



    }












    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new PerfilFragment(), "Perfil");

        adapter.AddFragment(new PokemonsFragment(), "Dex");

        adapter.AddFragment(new RegionsFragment(), "Regiões");
        viewPager.setAdapter(adapter);

    }

    public void ordemAz(View view) {
        Toast.makeText(getApplicationContext(),"Ordena  A-Z", Toast.LENGTH_SHORT).show();

    }

    public void ordemNum(View view) {
        Toast.makeText(getApplicationContext(),"Ordena  Numericamente", Toast.LENGTH_SHORT).show();
    }




}



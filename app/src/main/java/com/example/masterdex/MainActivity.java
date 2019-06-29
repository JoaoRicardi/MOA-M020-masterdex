package com.example.masterdex;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.masterdex.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.perfil_icon,
            R.drawable.dex_icon,
            R.drawable.regioes_icon,
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager_id);
        setupViewPager(viewPager);

        viewPager.setCurrentItem(1,true);


        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        setupIcons();

    }
        private void setupIcons() {
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

            adapter.AddFragment(new PerfilFragment(), "Perfil");

            adapter.AddFragment(new PokemonsFragment(), "Dex");

            adapter.AddFragment(new RegionsFragment(), "Regiões");
            viewPager.setAdapter(adapter);
        }
}





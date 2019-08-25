package com.example.masterdex.modules.regiaoinformacao.view;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.R;
import com.example.masterdex.adapter.RegioesViewPagerAdapter;
import com.example.masterdex.models.Regiao;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegioesInformacoesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nomeRegiaoTextView;
    private TextView descricaoRegiaoTextView;
    private ImageView botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regioes_informacoes);

        imageView = findViewById(R.id.foto_informacoes_image_view);
        nomeRegiaoTextView = findViewById(R.id.regioes_nome_text_view);
        descricaoRegiaoTextView = findViewById(R.id.texto_informacoes_text_view);
        botaoVoltar = findViewById(R.id.regioes_info_back_button);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Regiao regiao = (Regiao) Objects.requireNonNull(bundle).getSerializable("REGIAO");

        nomeRegiaoTextView.setText(Objects.requireNonNull(regiao).getNomeRegiao());

        Picasso.get().load(regiao.getImagemRegiao()).into(imageView);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            voltarParaRegiao();
            }
        });

        ViewPager viewPager = findViewById(R.id.regiao_view_pager_id);
        RegioesViewPagerAdapter myPagerAdapter = new RegioesViewPagerAdapter(getSupportFragmentManager(), regiao);
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.regioes_tab_layout);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void voltarParaRegiao(){
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}

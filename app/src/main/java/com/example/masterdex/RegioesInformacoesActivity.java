package com.example.masterdex;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.models.Regiao;
import com.squareup.picasso.Picasso;

public class RegioesInformacoesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nomeRegiaoTextView;
    private TextView descricaoRegiaoTextView;
    private ImageView botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regioes_informacoes);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Regiao regiao = (Regiao) bundle.getSerializable("REGIAO");

        imageView = findViewById(R.id.foto_informacoes_image_view);
        Picasso.get().load(regiao.getImagemRegiao()).into(imageView);
        nomeRegiaoTextView = findViewById(R.id.regioes_nome_text_view);
        nomeRegiaoTextView.setText(regiao.getNomeRegiao());
        descricaoRegiaoTextView = findViewById(R.id.texto_informacoes_text_view);
        descricaoRegiaoTextView.setText(regiao.getDescricaoRegiao());

        botaoVoltar = findViewById(R.id.regioes_info_back_button);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            voltarParaRegiao();
            }
        });


    }

    public void voltarParaRegiao(){
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}

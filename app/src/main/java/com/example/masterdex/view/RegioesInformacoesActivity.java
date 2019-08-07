package com.example.masterdex.view;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.R;
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

        imageView = findViewById(R.id.foto_informacoes_image_view);
        nomeRegiaoTextView = findViewById(R.id.regioes_nome_text_view);
        descricaoRegiaoTextView = findViewById(R.id.texto_informacoes_text_view);
        botaoVoltar = findViewById(R.id.regioes_info_back_button);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Regiao regiao = (Regiao) bundle.getSerializable("REGIAO");

        nomeRegiaoTextView.setText(regiao.getNomeRegiao());

        descricaoRegiaoTextView.setText(regiao.getDescricaoRegiao());
        Picasso.get().load(regiao.getImagemRegiao()).into(imageView);

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

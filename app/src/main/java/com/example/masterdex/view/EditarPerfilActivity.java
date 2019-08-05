package com.example.masterdex.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.masterdex.R;

public class EditarPerfilActivity extends AppCompatActivity {

    private ImageView botaoCancelarAlteracoes;
    private ImageView botaoSalvarAlteracoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        botaoCancelarAlteracoes = findViewById(R.id.button_cancelar_alteracoes);
        botaoSalvarAlteracoes = findViewById(R.id.button_salvar_alteracoes);

        botaoCancelarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sairEditarPerfil();
            }
        });

        botaoSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sairEditarPerfil();
            }
        });
    }

    public void sairEditarPerfil(){

            if (getFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
            } else {
                getFragmentManager().popBackStack();
            }


    }
}

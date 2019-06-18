package com.example.masterdex;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EsqueciASenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_asenha);

        TextInputEditText emailDigitado = findViewById(R.id.email_textinput);

        Button buttonEnviar = findViewById(R.id.enviar_button);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarClicado(view);
            }
        });
    }

    TextInputEditText emailDigitado = findViewById(R.id.email_textinput);


    public void enviarClicado (View view){

        if (emailDigitado.getText().toString().equals("")){

            emailDigitado.setError("Digite um email");

        } else {

            Snackbar.make(view, "Usuário cadastrado com sucesso", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setActionTextColor(getResources().getColor(R.color.branco)).show();

        }


    }
}

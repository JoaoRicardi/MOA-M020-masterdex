package com.example.masterdex.view;

import android.content.Intent;

import com.example.masterdex.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EsqueciASenha extends AppCompatActivity {

    private TextInputEditText emailDigitado;
    private ImageView buttonVoltar;
    private Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_asenha);


        initComponents();




        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarClicado(view);
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarParaLogin();
            }
        });


    }



    private void initComponents() {

        emailDigitado = findViewById(R.id.email_textinput);
        buttonVoltar = findViewById(R.id.botao_voltar_esqueci_senha);
        buttonEnviar = findViewById(R.id.enviar_button);

    }


    public void enviarClicado (View view){

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()){
            teclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);}
        //Em caso de teclado visível, este método esconde ele



        if (emailDigitado.getText().toString().equals("")){
            //Se não houver texto no campo do email, ele irá exibir o erro abaixo

            emailDigitado.setError("Digite seu email");

        } else {
            //Caso o campo de email tenha algo, aparecerá um snackbar dizendo que o usuário foi cadastrado
            //Clicar no "ok" do snackbar leva de volta para a tela de login

            Snackbar.make(view, "Email enviado", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            voltarParaLogin();

                        }
                    }).setActionTextColor(getResources().getColor(R.color.branco)).show();

        }




    }

    public void voltarParaLogin(){
        //Ao clicar na seta de voltar ele irá para a tela de login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

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

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText textEditNick,textEditEmail,textEditSenha,textEditConfirmarSenha;
    private ImageView botaoVoltarParaLogin;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastrar = findViewById(R.id.cadastrar_button);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastroRealizado(view);
            }
        });


        initComponents();

        botaoVoltarParaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarParaLogin();
            }
        });



    }

    private void initComponents() {

        textEditNick =findViewById(R.id.textEditNick);
        textEditEmail =findViewById(R.id.email_textinput);
        textEditSenha =findViewById(R.id.textEditSenha);
        textEditConfirmarSenha = findViewById(R.id.textEditConfirmarSenha);
        botaoVoltarParaLogin = findViewById(R.id.button_voltar_para_login);

    }

    public void voltarParaLogin(){

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    public void cadastroRealizado(){

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()){
            teclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);}
        //Em caso de teclado visível, este método esconde ele

        if (textEditNick.getText().toString().equals("")){
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditNick.setError("Digite um Nick");

        }
        if (textEditEmail.getText().toString().equals("")){
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditEmail.setError("Digite seu Email");

        }
        if (textEditSenha.getText().toString().equals("")){
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditSenha.setError("Digite uma Senha");

        }

        if (textEditConfirmarSenha.getText().toString().equals("")){
            //Se nãp huver nada no campo de senha aparecerá essa mensagem
            textEditConfirmarSenha.setError("Digite uma Senha");
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
    public void cadastroRealizado(View view){

        Snackbar.make(view, "Cadastro realizado com sucesso", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        voltarParaLogin();

                    }
                }).setActionTextColor(getResources().getColor(R.color.branco)).show();
    }




}

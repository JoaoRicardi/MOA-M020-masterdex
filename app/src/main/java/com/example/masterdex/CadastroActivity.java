package com.example.masterdex;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText textEditNick,textEditEmail,textEditSenha,textEditConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastrarUsuario();
        initComponents();



    }

    private void initComponents() {

        textEditNick =findViewById(R.id.textEditNick);
        textEditEmail =findViewById(R.id.email_textinput);
        textEditSenha =findViewById(R.id.textEditSenha);
        textEditConfirmarSenha = findViewById(R.id.textEditConfirmarSenha);

    }


    public void cadastrarUsuario(){

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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


}

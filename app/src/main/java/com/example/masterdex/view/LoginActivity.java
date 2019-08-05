package com.example.masterdex.view;

import android.content.Intent;

import com.example.masterdex.R;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button entrar;
    TextInputEditText emailDigitado;
    TextInputEditText senhaDigitada;
    TextView esqueciSenha;
    Button irParaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailDigitado = findViewById(R.id.login_email);
        senhaDigitada = findViewById(R.id.login_senha);
        entrar = findViewById(R.id.entrar_button);
        esqueciSenha = findViewById(R.id.login_esqueci_senha);
        irParaCadastro = findViewById(R.id.botao_ir_para_cadastro);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrarNaConta();
            }
        });

        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaEsqueciSenha();
            }
        });

        irParaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaTelaDeCadastro();
            }
        });
    }

    public void entrarNaConta(){

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()){
            teclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);}
        //Em caso de teclado visível, este método esconde ele

        if (emailDigitado.getText().toString().equals("")){
            //Se não houver nada no campo de email aparecerá essa mensagem
            emailDigitado.setError("Digite um email");

        }

        if (senhaDigitada.getText().toString().equals("")){
            //Se nãp huver nada no campo de senha aparecerá essa mensagem
            senhaDigitada.setError("Digite uma senha");
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void irParaEsqueciSenha(){
        Intent intent = new Intent(this,EsqueciASenha.class);
        startActivity(intent);
    }

    public void irParaTelaDeCadastro(){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}

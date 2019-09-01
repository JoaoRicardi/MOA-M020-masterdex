package com.example.masterdex.view;

import android.content.Intent;

import com.example.masterdex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class EsqueciASenha extends AppCompatActivity {

    private TextInputEditText emailDigitado;
    private ImageView buttonVoltar;
    private Button buttonEnviar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_asenha);


        auth = FirebaseAuth.getInstance();
        emailDigitado = findViewById(R.id.email_textinput);
        buttonVoltar = findViewById(R.id.botao_voltar_esqueci_senha);
        buttonEnviar = findViewById(R.id.enviar_button);


        buttonEnviar.setOnClickListener(this::enviarClicado);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarParaLogin();
            }
        });


    }

    private void trocarSenha() {
        auth.sendPasswordResetEmail(Objects.requireNonNull(emailDigitado.getText())
                .toString().toLowerCase())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Instruções enviadas")
                                .setContentText("Acesse seu email e siga os passos para mudar a senha.")
                                .show();

                    } else {

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Por favor, verifique se o email foi digitado corretamente")
                                .show();

                    }
                });
    }


    public void enviarClicado(View view) {

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()) {
            teclado.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        }
        //Em caso de teclado visível, este método esconde ele


        if (Objects.requireNonNull(emailDigitado.getText()).toString().equals("")) {
            //Se não houver texto no campo do email, ele irá exibir o erro abaixo

            emailDigitado.setError("Digite seu email");

        } else {
            //Caso o campo de email tenha algo, aparecerá um snackbar dizendo que o usuário foi cadastrado
            //Clicar no "ok" do snackbar leva de volta para a tela de login

            trocarSenha();

        }


    }

    public void voltarParaLogin() {
        //Ao clicar na seta de voltar ele irá para a tela de login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

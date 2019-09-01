package com.example.masterdex.view;

import android.content.Intent;

import com.example.masterdex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.annotations.NonNull;

public class CadastroActivity extends AppCompatActivity {

    private static final String TAG = "CadastroActivity";
    private TextInputEditText textEditNick;
    private TextInputEditText textEditEmail;
    private TextInputEditText textEditSenha;
    private TextInputEditText textEditConfirmarSenha;
    private ImageView botaoVoltarParaLogin;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        Button cadastrar = findViewById(R.id.cadastrar_button);

        cadastrar.setOnClickListener(view -> cadastroRealizado());


        initComponents();

        botaoVoltarParaLogin.setOnClickListener(view -> voltarParaLogin());


    }

    private void initComponents() {

        textEditNick = findViewById(R.id.nickCadastro);
        textEditEmail = findViewById(R.id.emailCadastro);
        textEditSenha = findViewById(R.id.senhaCadastro);
        textEditConfirmarSenha = findViewById(R.id.confiSenhaCadastro);
        botaoVoltarParaLogin = findViewById(R.id.button_voltar_para_login);

    }

    public void voltarParaLogin() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void cadastroRealizado() {

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()) {
            teclado.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        }
        //Em caso de teclado visível, este método esconde ele

        if (Objects.requireNonNull(textEditNick.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditNick.setError("Digite um Nick");

        }
        if (Objects.requireNonNull(textEditEmail.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditEmail.setError("Digite seu Email");

        }
        if (Objects.requireNonNull(textEditSenha.getText()).toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            textEditSenha.setError("Digite uma Senha");

        }

        if (Objects.requireNonNull(textEditConfirmarSenha.getText()).toString().equals("")) {
            //Se nãp huver nada no campo de senha aparecerá essa mensagem
            textEditConfirmarSenha.setError("Digite uma Senha");
        } else {
            cadastrar();

        }

    }


    private void cadastrar() {
        String email = textEditEmail.getEditableText().toString();
        String password = textEditSenha.getEditableText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");

                        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Cadastro")

                                .setContentText("Cadastro realizado com sucesso!")

                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        voltarParaLogin();
                                    }
                                })
                                .show();

                        //

                        //atualizarPerfil();


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());

                        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Por favor revise seus dados, talvez o usuario ja exista no sistema")
                                .show();
                        //    Toasty.error(CadastroActivity.this,
                        //            "Por favor revise seus dados ,talvez o usuario ja existe no sistema",
                        //            Toasty.LENGTH_SHORT).show();

                    }

                });
    }

    public void atualizarPerfil() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(textEditNick.getEditableText().toString())
                .build();

        assert user != null;
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Seu cadastro foi atualizado.");
                            new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Cadastro")
                                    .setContentText("Cadastro atualizado com sucesso!")
                                    .show();
                        }
                    }
                });
    }

}

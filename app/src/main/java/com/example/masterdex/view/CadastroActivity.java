package com.example.masterdex.view;

import android.content.Context;
import android.content.Intent;

import com.example.masterdex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import io.reactivex.annotations.NonNull;

public class CadastroActivity extends AppCompatActivity {

    private static final String TAG = "CadastroActivity";
    private TextInputEditText textEditNick;
    private TextInputEditText textEditEmail;
    private TextInputEditText textEditSenha;
    private TextInputEditText textEditConfirmarSenha;
    private ImageView botaoVoltarParaLogin;
    private Button cadastrar;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        cadastrar = findViewById(R.id.cadastrar_button);

        cadastrar.setOnClickListener(view -> cadastrar());


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

    public void cadastroRealizado(View view) {

        Snackbar.make(view, "Cadastro realizado com sucesso", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        voltarParaLogin();

                    }
                }).setActionTextColor(getResources().getColor(R.color.branco)).show();
    }

    private void cadastrar() {
        String email = textEditEmail.getEditableText().toString();
        String password = textEditSenha.getEditableText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");

                        Toast.makeText(CadastroActivity.this,
                                "Cadastro realizado com sucesso",
                                Toast.LENGTH_SHORT).show();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        voltarParaLogin();
                        atualizarPerfil();




                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(CadastroActivity.this,
                                "Por favor revise seus dados ,talvez o usuario ja existe no sistema",
                                Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(CadastroActivity.this, "Seu cadastro foi atualizado.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}

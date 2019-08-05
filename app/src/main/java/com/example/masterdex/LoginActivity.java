package com.example.masterdex;

import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Transaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    Button entrar;
    TextInputEditText emailDigitado;
    TextInputEditText senhaDigitada;
    TextView esqueciSenha;
    Button irParaCadastro;
    private LoginButton loginFacebook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailDigitado = findViewById(R.id.login_email);
        senhaDigitada = findViewById(R.id.login_senha);
        entrar = findViewById(R.id.entrar_button);
        esqueciSenha = findViewById(R.id.login_esqueci_senha);
        irParaCadastro = findViewById(R.id.botao_ir_para_cadastro);
        loginFacebook =  findViewById(R.id.entrar_com_fb_button);

        callbackManager = CallbackManager.Factory.create();

        loginFacebook.setReadPermissions("email");

        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Falha ao logar", Toast.LENGTH_SHORT).show();
            }
        });


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

        }
    };

    private void loaduserProfile (AccessToken accessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String first_name =  object.getString("first_name");
                    String last_name =  object.getString("last_name");
                    String email =  object.getString("email");
                    String id = object.getString("id");

                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    Bundle bundle = new Bundle();
                    bundle.putString("FOTO", image_url);
                    bundle.putString("NOME", first_name);
                    bundle.putString("SOBRENOME", last_name);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    PerfilFragment perfilFragment = new PerfilFragment();
                    perfilFragment.setArguments(bundle);
                    transaction.replace(R.id.viewpager_id, perfilFragment);
                    transaction.commit();

                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "first_name, last_name, email, id");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }}


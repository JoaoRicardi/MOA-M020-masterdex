package com.example.masterdex.view;

import android.content.Intent;

import com.example.masterdex.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
    private LoginButton loginComFacebook;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        emailDigitado = findViewById(R.id.email_login);
        senhaDigitada = findViewById(R.id.senha_login);
        entrar = findViewById(R.id.entrar_login_button);
        esqueciSenha = findViewById(R.id.login_esqueci_senha);
        irParaCadastro = findViewById(R.id.botao_ir_para_cadastro);
        loginComFacebook = findViewById(R.id.entrar_com_fb_button);


        callbackManager = CallbackManager.Factory.create();
        loginComFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));

           /* AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null) {
                informacoesUsuarioFace(accessToken);
            }*/

       /* loginComFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                informacoesUsuarioFace(accessToken);
                irParaMain();
                Toast.makeText(getApplicationContext(), "Chamou register Callback", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });*/

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        //loginComFacebook.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //AccessToken accessToken = AccessToken.getCurrentAccessToken();
               // if (accessToken != null){
                 //   LoginManager.getInstance().logOut();
                //}else {
                //}

        //    }
      //  });

        loginComFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginComFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = loginResult.getAccessToken();
                        informacoesUsuarioFace(accessToken);
                        irParaMain();
                        Toast.makeText(getApplicationContext(), "Chamou register Callback", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void informacoesUsuarioFace(AccessToken accessToken){

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name, picture.width(200)");
        request.setParameters(parameters);

        request.executeAsync();

    }


    private void irParaTelaDeCadastro() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    public void entrarNaConta() {

        android.view.inputmethod.InputMethodManager teclado = (android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (teclado.isAcceptingText()) {
            teclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        //Em caso de teclado visível, este método esconde ele

        if (emailDigitado.getText().toString().equals("")) {
            //Se não houver nada no campo de email aparecerá essa mensagem
            emailDigitado.setError("Digite um email");

        }

        if (senhaDigitada.getText().toString().equals("")) {
            //Se nãp huver nada no campo de senha aparecerá essa mensagem
            senhaDigitada.setError("Digite uma senha");
        } else {
            logar();

        }

    }

    public void irParaEsqueciSenha() {
        Intent intent = new Intent(this, EsqueciASenha.class);
        startActivity(intent);
    }

    public void irParaMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void logar() {
        String email = emailDigitado.getEditableText().toString();
        String senha = senhaDigitada.getEditableText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            irParaMain();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}

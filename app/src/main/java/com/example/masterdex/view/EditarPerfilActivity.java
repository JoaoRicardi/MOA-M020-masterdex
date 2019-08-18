package com.example.masterdex.view;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.masterdex.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.annotations.NonNull;

public class EditarPerfilActivity extends AppCompatActivity {

    private static final String TAG = "EditarPerfilActivity";
    private ImageView botaoCancelarAlteracoes;
    private ImageView botaoSalvarAlteracoes;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private TextView nomePerfil;
    private TextInputEditText nomeEdit;
    private CircleImageView fotoPerfil;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


        nomePerfil = findViewById(R.id.nome_editar_perfil);
        nomeEdit = findViewById(R.id.nome_edit_perfil);
        fotoPerfil = findViewById(R.id.foto_avatar_editar_perfil);

        botaoCancelarAlteracoes = findViewById(R.id.button_cancelar_alteracoes);
        botaoSalvarAlteracoes = findViewById(R.id.button_salvar_alteracoes);

        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            nomePerfil.setText(name);
            baixarFoto();
        }


        fotoPerfil.setOnClickListener(v -> {
            Toast.makeText(EditarPerfilActivity.this, "Bora tirar uma Foto", Toast.LENGTH_SHORT).show();
            abrirCamera();
        });


        botaoCancelarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botaoSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    atualizarDadosUsuario();

                } else {
                    finish();
                }


            }
        });


    }


    private void abrirCamera() {
        Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (irParaCamera.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(irParaCamera, REQUEST_IMAGE_CAPTURE);
        }

    }


    private void atualizarDadosUsuario() {
        String nome = nomeEdit.getEditableText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                        }
                    }
                });

    }

    private void irParaPerfil() {

        Intent intent = new Intent(this, PerfilFragment.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = intent.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fotoPerfil.setImageBitmap(imageBitmap);

            StorageReference reference = storage.getReference("perfil/" + user.getUid());

            // Get the data from an ImageView as bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = reference.putBytes(data);
            uploadTask.addOnFailureListener(exception -> Toast.makeText(getApplicationContext(), "Upload falhou", Toast.LENGTH_SHORT).show())
                    .addOnSuccessListener(taskSnapshot -> Toast.makeText(getApplicationContext(), "Upload concluído", Toast.LENGTH_SHORT).show());

        }
    }

    private void baixarFoto() {

        StorageReference reference = storage.getReference("perfil/" + user.getUid());

        reference.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(fotoPerfil))
                .addOnFailureListener(exception -> Toast.makeText(getApplicationContext(),
                        "Erro ao baixar foto",
                        Toast.LENGTH_SHORT).show());
    }
}

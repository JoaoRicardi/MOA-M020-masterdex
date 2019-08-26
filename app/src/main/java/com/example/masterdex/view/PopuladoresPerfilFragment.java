package com.example.masterdex.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.adapter.AdapterPerfilPopulares;
import com.example.masterdex.models.Pokemon;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.annotations.NonNull;

public class PopuladoresPerfilFragment extends Fragment {

    private static final String TAG = "PopuladoresPerfilFragment";
    private RecyclerView recyclerView;
    private AdapterPerfilPopulares adapterPerfilPopulares;
    private static final int LIMIT = 20;
    private int offset = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user;
    public String nomePokemonPopular;
    public String contador;
    List<Pokemon> novaLista = new ArrayList<>();
    private int i;


    public PopuladoresPerfilFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_populares_perfil, container, false);


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
        }


        recyclerView = view.findViewById(R.id.populares_perfil_recyclerview_id);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPerfilPopulares);


        //   GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        //   recyclerView.setLayoutManager(layoutManager);
        //   recyclerView.setAdapter(adapterPerfilPopulares);


        buscarDadosFirebase();


        return view;
    }

    private void buscarDadosFirebase() {
        db.collection("votações")
                .document("pokemons")
                .collection("populares")
                .orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        List<Pokemon> pokemonList=new ArrayList<>();
                        for (QueryDocumentSnapshot doc : (value)) {

                            Pokemon pokemon = new Pokemon();
                            pokemon.setName((String) doc.getData().get("nome"));
                            System.out.println(pokemon.getName());
                            pokemonList.add(pokemon);







                        }



                        adapterPerfilPopulares = new AdapterPerfilPopulares(pokemonList);


                    }


                });
    }


}
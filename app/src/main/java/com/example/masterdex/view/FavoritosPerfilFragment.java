package com.example.masterdex.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.masterdex.R;
import com.example.masterdex.adapter.AdapterPerfilFavoritos;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import java.util.Objects;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

import static com.example.masterdex.repository.DetalhesPokemonRepository.FAVORITOS_DB;

public class FavoritosPerfilFragment extends Fragment {

    private RecyclerView favRv;
    private AdapterPerfilFavoritos favoritos;
    private FirebaseStorage storage;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FavoritosDb favoritosDb;

    public FavoritosPerfilFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos_perfil, container, false);

        favoritosDb = Room.databaseBuilder(Objects.requireNonNull(getContext()),
                FavoritosDb.class, FAVORITOS_DB).build();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
        }
        favRv = view.findViewById(R.id.favoritos_perfil_recyclerview_id);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        favRv.setLayoutManager(layoutManager);
        favRv.setAdapter(favoritos);
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        buscarTudoNoRoom();
        return view;
    }

    @Override
    public void onStart() {
        buscarTudoNoRoom();
        super.onStart();
    }


    private void buscarTudoNoRoom() {
        FavoritosDao favoritosDao = favoritosDb.favoritosDao();
        favoritosDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {

                    favoritos = new AdapterPerfilFavoritos(pokemonEncontrado,getActivity());

                });

    }
}

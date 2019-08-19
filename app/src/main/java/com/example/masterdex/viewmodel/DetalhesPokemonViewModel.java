package com.example.masterdex.viewmodel;

import android.app.Application;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.masterdex.database.CapturadosDao;
import com.example.masterdex.database.CapturadosDb;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.repository.DetalhesPokemonRepository;
import com.example.masterdex.repository.PokemonRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetalhesPokemonViewModel extends AndroidViewModel {


    private MutableLiveData<Pokemon> pokemonLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<Pokemon> getPokemonLiveData() {
        return pokemonLiveData;
    }

    private PokemonRepository pokemonRepository = new PokemonRepository();



    public DetalhesPokemonViewModel(@NonNull Application application) {
        super(application);
    }


    public void getPokemonByName(String name) {
        disposable.add(
          pokemonRepository.getPokemonByName(name)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.newThread())
          .subscribe(pokemon -> pokemonLiveData.setValue(pokemon))
        );
    }
}

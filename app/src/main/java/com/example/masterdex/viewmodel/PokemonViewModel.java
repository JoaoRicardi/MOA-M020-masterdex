package com.example.masterdex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.masterdex.models.Pokemon;
import com.example.masterdex.repository.PokemonRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PokemonViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pokemon>> pokemonLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private PokemonRepository pokemonRepository = new PokemonRepository();


    public PokemonViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Pokemon>> getPokemonLiveData() {
        return pokemonLiveData;
    }
    public void atualizarPokemon(int limit, int offset){

        disposable.add(
                pokemonRepository.getPokeListApi(limit,offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonList -> pokemonLiveData.setValue(pokemonList),
                        Throwable::printStackTrace)
        );
    }
}

package com.example.masterdex.modules.regiaoinformacao.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.masterdex.models.RegiaoPokemon;
import com.example.masterdex.repository.RegioesInformacoesRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegioesPokemonViewModel extends AndroidViewModel {

    private MutableLiveData<List<RegiaoPokemon>> regiaoPokemonLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RegioesInformacoesRepository regiaoPokemonRepository = new RegioesInformacoesRepository();

    public RegioesPokemonViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<List<RegiaoPokemon>> getRegiaoPokemonLiveData(){
        return regiaoPokemonLiveData;
    }

    public void atualizarPokemon(String nomeRegiao,int limit, String sort){
        disposable.add(
                regiaoPokemonRepository.getPokemonRegionList(nomeRegiao, limit, sort)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe()
        );
    }
}

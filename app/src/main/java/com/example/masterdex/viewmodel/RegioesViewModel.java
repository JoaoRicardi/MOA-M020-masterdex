package com.example.masterdex.viewmodel;

import android.app.Application;

import com.example.masterdex.models.Regiao;
import com.example.masterdex.repository.RegioesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegioesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Regiao>> mutableLiveData = new MutableLiveData<>();
    private RegioesRepository regioesRepository = new RegioesRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    public RegioesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Regiao>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void atualizarRegioes() {
        disposable.add(
                regioesRepository.getListaRegioes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(regioes -> mutableLiveData.setValue(regioes))

        );
    }
}

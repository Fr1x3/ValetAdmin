package com.kaps.valetadmin.viewmodel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kaps.valetadmin.Repository.RegisterRepository;
import com.kaps.valetadmin.models.Valet;

import java.util.List;

public class ValetListViewmodel extends AndroidViewModel {

    private final RegisterRepository mRepository;

    public ValetListViewmodel(@NonNull Application application) {
        super(application);

        mRepository = new RegisterRepository();
    }

    public LiveData<List<Valet>> getValet(){
        return mRepository.getValets();
    }
}

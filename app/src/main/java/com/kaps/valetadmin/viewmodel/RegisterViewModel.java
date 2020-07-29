package com.kaps.valetadmin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kaps.valetadmin.Repository.RegisterRepository;
import com.kaps.valetadmin.models.MessageResponse;
import com.kaps.valetadmin.models.Valet;

public class RegisterViewModel extends AndroidViewModel {

    private final RegisterRepository mRegisterRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        mRegisterRepository = new RegisterRepository();
    }

    public LiveData<MessageResponse> register(Valet valet){
        return mRegisterRepository.register(valet);
    }
}

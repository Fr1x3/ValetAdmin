package com.kaps.valetadmin.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kaps.valetadmin.models.MessageResponse;
import com.kaps.valetadmin.models.Valet;
import com.kaps.valetadmin.network.RegisterService;
import com.kaps.valetadmin.network.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {

    private RegisterService mRegisterService;
    MutableLiveData<MessageResponse> mValetMutableLiveData;
    MutableLiveData<List<Valet>> mValets;

    public RegisterRepository(){
        mRegisterService = ServiceBuilder.createService(RegisterService.class);
    }

    public MutableLiveData<MessageResponse> register(Valet valet){
        mValetMutableLiveData = new MutableLiveData<>();

        mRegisterService.addValet(valet).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if( response.body() != null && response.code() == 200)
                    mValetMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                t.getStackTrace();
                Log.d("register", t.getMessage());

            }
        });

        return mValetMutableLiveData;
    }

    public MutableLiveData<List<Valet>> getValets(){
        mValets = new MutableLiveData<>();

        mRegisterService.getvalet().enqueue(new Callback<List<Valet>>() {
            @Override
            public void onResponse(Call<List<Valet>> call, Response<List<Valet>> response) {
                if( response.body() != null && response.code() == 200){
                    mValets.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Valet>> call, Throwable t) {
                mValets.setValue(null);
            }
        });
        return mValets;
    }

}

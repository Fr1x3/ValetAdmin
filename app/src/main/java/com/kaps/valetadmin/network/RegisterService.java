package com.kaps.valetadmin.network;

import com.kaps.valetadmin.models.MessageResponse;
import com.kaps.valetadmin.models.Valet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("valet")
    Call<MessageResponse> addValet(@Body Valet valet);

    @GET("valets")
    Call<List<Valet>> getvalet();
}

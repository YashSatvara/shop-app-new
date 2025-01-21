package com.example.shopapp.Webservice;

import com.example.shopapp.Model.MsgModal;
import com.example.shopapp.Model.ResultSavelogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<MsgModal> getMessage(@Url String url);

    @FormUrlEncoded
    @POST("registration.php") // Correct
    Call<ResultSavelogin> insert(
            @Field("uemail") String email,
            @Field("upassword") String password
    );

}
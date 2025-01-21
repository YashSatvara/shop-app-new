package com.example.shopapp.Webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Appclient {

    public static final String BASEURL = "http://touchofgold.infinityfreeapp.com/"; // Use your actual domain and ensure it ends with '/'

    public static Retrofit getclient() {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

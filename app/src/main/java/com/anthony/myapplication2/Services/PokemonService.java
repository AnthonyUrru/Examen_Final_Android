package com.anthony.myapplication2.Services;

import com.anthony.myapplication2.ContactClass.ContatctClass222;
import com.anthony.myapplication2.ContactClass.PokemonClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PokemonService {
    @GET("N00024285")
    Call<List<PokemonClass>> getAll();
    @POST("N00024285/crear")
    Call<PokemonClass> create(@Body PokemonClass pokemon);
}

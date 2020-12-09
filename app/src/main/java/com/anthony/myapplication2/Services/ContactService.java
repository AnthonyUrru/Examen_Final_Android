package com.anthony.myapplication2.Services;

import com.anthony.myapplication2.ContactClass.ContatctClass222;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContactService {
    @GET("c206c94d-3a28-4b85-9248-fe2bdfaebc45")
    Call<List<ContatctClass222>> getAll();

}

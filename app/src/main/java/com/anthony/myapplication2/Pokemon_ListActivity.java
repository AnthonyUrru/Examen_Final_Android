package com.anthony.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anthony.myapplication2.Adapers.PokemonAdapter;
import com.anthony.myapplication2.ContactClass.PokemonClass;
import com.anthony.myapplication2.Services.PokemonService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pokemon_ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        RecyclerView recyclerView = findViewById(R.id.reciPokemon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://upn.lumenes.tk/pokemons/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService service = retrofit.create(PokemonService.class);
        Call<List<PokemonClass>> call = service.getAll();
        call.enqueue(new Callback<List<PokemonClass>>() {
            @Override
            public void onResponse(Call<List<PokemonClass>> call, Response<List<PokemonClass>> response) {
                if(response.code()==200){
                    List<PokemonClass> pokemons= response.body();
                    Log.i("MAIN_APP", "OK Conexion exitosa");
                    Log.i("MAIN_APP", new Gson().toJson(pokemons));
                    Pokemon_ListActivity act2= Pokemon_ListActivity.this;
                    PokemonAdapter adapter= new PokemonAdapter(pokemons,act2);
                    recyclerView.setAdapter(adapter);
                }else{
                    Log.i("MAIN_APP", "Error de aplicacion");
                }
            }

            @Override
            public void onFailure(Call<List<PokemonClass>> call, Throwable t) {
                Log.i("MAIN_APP","No pudimos conectarnos al servidos");
            }
        });



    }
}
package com.anthony.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anthony.myapplication2.Adapers.ContactAdapter;
import com.anthony.myapplication2.ContactClass.ContatctClass222;
import com.anthony.myapplication2.Services.ContactService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        RecyclerView recyclerView = findViewById(R.id.rvContact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Mas importante
/*
        ContactClass a=new ContactClass();

        a.nombre="Juan";
        a.numero="92475873";
        ContactClass b=new ContactClass();

        b.nombre="Luis";
        b.numero="99995782";
        ContactClass c=new ContactClass();

        c.nombre="Fernando";
        c.numero="95697864";
        ContactClass d=new ContactClass();

        d.nombre="Ulises";
        d.numero="95555555";
        ContactClass e=new ContactClass();
        e.nombre="Salomon";
        e.numero="915321452";
        List<ContactClass> data= new ArrayList<>();
        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);
        data.add(e);
*/
        //Crear el retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactService service = retrofit.create(ContactService.class);
        Call<List<ContatctClass222>> call = service.getAll();
        call.enqueue(new Callback<List<ContatctClass222>>() {
            @Override
            public void onResponse(Call<List<ContatctClass222>> call, Response<List<ContatctClass222>> response) {
            if(response.code()==200){
               List<ContatctClass222> data= response.body();
                Log.i("MAIN_APP", "OK Conexion exitosa");
                SecondActivity2 act=SecondActivity2.this;
                ContactAdapter adapter= new ContactAdapter(data,act);
                recyclerView.setAdapter(adapter);
            }else{
                Log.i("MAIN_APP", "Error de aplicacion");
            }
            }

            @Override
            public void onFailure(Call<List<ContatctClass222>> call, Throwable t) {
            Log.i("MAIN_APP","No pudimos conectarnos al servidos");
            }
        });






    }
}
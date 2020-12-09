package com.anthony.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__pokemon);

        Button Pokebtn = findViewById(R.id.btnMispoke);
        Button CPokebtn = findViewById(R.id.BtnCrearPoke);

        Pokebtn.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View v) {

              startActivity(new Intent(Main_PokemonActivity.this,Pokemon_ListActivity.class));
          }
      });
        CPokebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_PokemonActivity.this,Create_PokemonActivity.class));
            }
        });
    }
}
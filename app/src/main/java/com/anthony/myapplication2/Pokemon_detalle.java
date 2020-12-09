package com.anthony.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pokemon_detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detalle);
        Button btnUbicacion = findViewById(R.id.BtnUbicacion);
        TextView nombre= findViewById(R.id.texDnombre);
        TextView tipo= findViewById(R.id.texDtipo);
        TextView latitud= findViewById(R.id.texDlatitud);
        TextView longitud= findViewById(R.id.texDlongitud);
        Intent intent= getIntent();
        nombre.setText(intent.getStringExtra("P_nombre"));
        tipo.setText(intent.getStringExtra("P_tipo"));
        latitud.setText(intent.getStringExtra("P_latitud"));
        longitud.setText(intent.getStringExtra("P_longitud"));
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Pokemon_detalle.this,Pokemon_Ubicacion.class);
                intent2.putExtra("P_p_latitud",intent.getStringExtra("P_latitud"));
                intent2.putExtra("P_p_longitud",intent.getStringExtra("P_longitud"));
                intent2.putExtra("P_p_nombre",intent.getStringExtra("P_nombre"));
                startActivity(intent2);

            }
        });
    }
}
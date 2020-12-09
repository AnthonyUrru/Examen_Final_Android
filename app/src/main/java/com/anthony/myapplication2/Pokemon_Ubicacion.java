package com.anthony.myapplication2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Pokemon_Ubicacion extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon__ubicacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Intent intent= getIntent();
        String latitud=intent.getStringExtra("P_p_latitud");
        String longitud=intent.getStringExtra("P_p_longitud");
        String nombre=intent.getStringExtra("P_p_nombre");
        float flatitud = Float.parseFloat(latitud);
        float flongitud = Float.parseFloat(longitud);
        LatLng pokemonUbic = new LatLng(flatitud, flongitud);
        mMap.addMarker(new MarkerOptions().position(pokemonUbic).title("Aqui esta el pokemon '"+nombre+"'"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pokemonUbic,15f));
    }
}
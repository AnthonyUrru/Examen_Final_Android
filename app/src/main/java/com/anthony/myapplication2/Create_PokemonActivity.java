package com.anthony.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.anthony.myapplication2.ContactClass.PokemonClass;
import com.anthony.myapplication2.Services.PokemonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create_PokemonActivity extends AppCompatActivity {
static final int REQUEST_PICK_GALLERY=2;
public ImageView pokePhotho;
public String img_b64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__pokemon);
        EditText textNombre = (EditText)findViewById(R.id.TPnombre);
        EditText textTipo = (EditText)findViewById(R.id.TPtipo);
        EditText textLatitud = (EditText)findViewById(R.id.TPlatitud);
        EditText textLongitud = (EditText)findViewById(R.id.TPlongitud);
        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        Button btnPthoto = findViewById(R.id.BtnRegiImg);
        pokePhotho=findViewById(R.id.imgCreate);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1003);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //byte[] decoderString= Base64.decode(img_b64, Base64.DEFAULT);
                //Bitmap decoded = BitmapFactory.decodeByteArray(decoderString,0,decoderString.length);
                //pokePhotho.setImageBitmap(decoded);
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://upn.lumenes.tk/pokemons/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokemonService service = retrofit.create(PokemonService.class);
                PokemonClass pokemon = new PokemonClass();
                pokemon.setNombre(textNombre.getText().toString());
                pokemon.setTipo(textTipo.getText().toString());
                pokemon.setLatitude(textLatitud.getText().toString());
                pokemon.setLongitude(textLongitud.getText().toString());
                //pokemon.setImagen(img_b64);
                Call<PokemonClass> call = service.create(pokemon);
                call.enqueue(new Callback<PokemonClass>() {
                    @Override
                    public void onResponse(Call<PokemonClass> call, Response<PokemonClass> response) {}
                    @Override
                    public void onFailure(Call<PokemonClass> call, Throwable t) { }});
                Toast.makeText(Create_PokemonActivity.this,"Ha nacido",Toast.LENGTH_SHORT).show();
            }
        });btnPthoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirImgGallery();
            }
        }); }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_PICK_GALLERY&&resultCode==RESULT_OK){
            Uri path=data.getData();
            pokePhotho.setImageURI(path);
        }
/*
        if (requestCode == REQUEST_PICK_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColum = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColum, null, null, null);
            cursor.moveToFirst();
            int columIndex = cursor.getColumnIndex(filePathColum[0]);
            String picturePath = cursor.getString(columIndex);
            cursor.close();
            pokePhotho.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }*/
    }
    private void abrirImgGallery(){
        Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i.createChooser(i,"Seleecione la Aplicacion"),REQUEST_PICK_GALLERY);
    }

  
}



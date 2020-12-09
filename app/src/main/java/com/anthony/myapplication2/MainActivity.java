package com.anthony.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private final int PERMISOS = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        Button btn2=findViewById(R.id.button2);
        Button btn3=findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.i("MY_APP", "Click en el boton");
                if(TienePermisosParaLlamar()){
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:925437347"));
                    startActivity(intent);
                }else{
                    SolicitarPermisoParaLLamar();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               if(TienePermisosParaMensaje()){
                   //Intent intent = new Intent(Intent.ACTION_SEND);
                   //intent.setData(Uri.parse("tel:Hola"));
                   SmsManager sms = SmsManager.getDefault();
                   sms.sendTextMessage("977694440",null,"Hola buen dia",null,null);
               }else{
                  SolicitarPermisoParaMensaje();
               }
             }
           }
        );
        btn3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/html");
            emailIntent.putExtra(Intent.EXTRA_EMAIL,"0144urrunaga@gmail.com");
            emailIntent.putExtra(Intent.EXTRA_TITLE,"Mi aplicacion");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Android Studio");
            emailIntent.putExtra(Intent.EXTRA_TEXT,"Este es un correo de mi nueva aplicacion");
            startActivity(Intent.createChooser(emailIntent,"Send Email"));
            }
        }
        );


    }

    private void SolicitarPermisoParaLLamar() {
        Log.i("MIN_APP","Solicitar Permiso");
        String[] permisos= new String[]{Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(this,permisos,PERMISOS );
    }

    private boolean TienePermisosParaLlamar() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED;
    }

    private void SolicitarPermisoParaMensaje() {
        Log.i("MIN_APP","Solicitar Permiso");
        String[] permisos2= new String[]{Manifest.permission.SEND_SMS};
        ActivityCompat.requestPermissions(this,permisos2,PERMISOS );
    }

    private boolean TienePermisosParaMensaje() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED;
    }
}
package com.anthony.myapplication2.Adapers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.myapplication2.ContactClass.ContatctClass222;
import com.anthony.myapplication2.R;
import com.anthony.myapplication2.SecondActivity2;
import com.squareup.picasso.Picasso;

import java.util.List;

//La clase adapter necesita extender de acapter y manda viewHolder
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
private static final String url="https://static.t13.cl/images/original/2016/04/1460154368-hqdefault.jpg";
    private final int PERMISOS = 1001;
private List<ContatctClass222> mData;
private SecondActivity2 act2;

public ContactAdapter(List<ContatctClass222> data, SecondActivity2 act){
mData=data;
act2=act;
}
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
TextView tvContact = holder.itemView.findViewById(R.id.TxNombre);
TextView tvNumero = holder.itemView.findViewById(R.id.TxTipo);
Button btnCall = holder.itemView.findViewById(R.id.buttonCall);
Button btnSms = holder.itemView.findViewById(R.id.btnsms);
ImageView imgn=holder.itemView.findViewById(R.id.ImgPokemon);

ContatctClass222 text = mData.get(position);
tvContact.setText(text.getName());
tvNumero.setText(text.getName());
        Picasso.get().load(url).into(imgn);
btnCall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(TienePermisosParaLlamar()){
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+text.getName()));
           act2.startActivity(intent);
        }else{
            SolicitarPermisoParaLLamar();
        }
    }
});
        btnSms.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Uri sms_uri=Uri.parse("smsto:+"+text.getName());
                                        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
                                        sms_intent.putExtra("sms_body","Hola como estas");
                                        act2.startActivity(sms_intent);
                                           // Intent intent = new Intent(Intent.ACTION_SEND);
                                            //intent.setData(Uri.parse("tel:Hola"));
                                            //SmsManager sms = SmsManager.getDefault();
                                            //sms.sendTextMessage("977694440",null,"Hola buen dia",null,null);
                                    }
                                }
        );

    }
    //Metodos para solicitar permisos
    private void SolicitarPermisoParaLLamar() {
        Log.i("MIN_APP","Solicitar Permiso");
        String[] permisos= new String[]{Manifest.permission.CALL_PHONE};

        ActivityCompat.requestPermissions(act2,permisos,PERMISOS );
    }

    private boolean TienePermisosParaLlamar() {

        return ContextCompat.checkSelfPermission(act2, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public int getItemCount() {

    return mData.size();
    }
    //**Finallmetodospara solicitar permidos
//Para cada clase adapter necesito una clase viewHolder
    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

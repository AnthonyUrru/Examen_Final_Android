package com.anthony.myapplication2.Adapers;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthony.myapplication2.ContactClass.PokemonClass;
import com.anthony.myapplication2.Pokemon_ListActivity;
import com.anthony.myapplication2.Pokemon_detalle;
import com.anthony.myapplication2.R;

import java.util.List;

//La clase adapter necesita extender de acapter y manda viewHolder
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
private static final String url="https://static.t13.cl/images/original/2016/04/1460154368-hqdefault.jpg";
    private final int PERMISOS = 1001;
private List<PokemonClass> mData;
private Pokemon_ListActivity act2;

public PokemonAdapter(List<PokemonClass> data, Pokemon_ListActivity act){
mData=data;
act2=act;
}
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list,parent,false);
       PokemonViewHolder vh = new PokemonViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
TextView txNombrep = holder.itemView.findViewById(R.id.TxNombre);
TextView txTipo = holder.itemView.findViewById(R.id.TxTipo);
//TextView txLatitudeP = holder.itemView.findViewById(R.id.txLatitude);
//TextView txLongitudeP = holder.itemView.findViewById(R.id.txLongitude);
Button botondetalle = holder.itemView.findViewById(R.id.BtnDetallePok);
//ImageView imgn=holder.itemView.findViewById(R.id.ImgPokemon);

PokemonClass text = mData.get(position);
txNombrep.setText(text.getNombre());
txTipo.setText(text.getTipo());
//txLatitudeP.setText(text.getLatitude());
//txLongitudeP.setText(text.getLongitude());
       // Picasso.get().load(url).into(imgn);
        botondetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(act2.getBaseContext(), Pokemon_detalle.class);
                intent.putExtra("P_nombre",text.getNombre());
                intent.putExtra("P_tipo",text.getTipo());
                intent.putExtra("P_latitud",text.getLatitude());
                intent.putExtra("P_longitud",text.getLongitude());
                act2.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

    return mData.size();
    }
    //**Finallmetodospara solicitar permidos
//Para cada clase adapter necesito una clase viewHolder
    public class PokemonViewHolder extends RecyclerView.ViewHolder{

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}

package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.GoleadorModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GoleadorRecyclerViewAdapter extends RecyclerView.Adapter<GoleadorRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNGoleador,txtNomEquipo,txtGoles;
        private ImageView imgGoleador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNGoleador = (TextView) itemView.findViewById(R.id.txtNGoleador);
            txtNomEquipo = (TextView) itemView.findViewById(R.id.txtNomEquipo);
            txtGoles = (TextView) itemView.findViewById(R.id.txtGoles);
            imgGoleador = (ImageView) itemView.findViewById(R.id.imgGoleador);
        }
    }

    public List<GoleadorModel> goleadoresLista;

    public GoleadorRecyclerViewAdapter(List<GoleadorModel> goleadoresLista) {
        this.goleadoresLista = goleadoresLista;
    }

    @Override
    public GoleadorRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goleador,parent,false);
        GoleadorRecyclerViewAdapter.ViewHolder viewHolder = new GoleadorRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoleadorRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNGoleador.setText(goleadoresLista.get(position).getJugador().getPrimerNombre() + " "
                                    + goleadoresLista.get(position).getJugador().getPrimerApellido());
        holder.txtNomEquipo.setText(goleadoresLista.get(position).getJugador().getEquipo().getNombre());
        String textoGoles="goles";
        if(goleadoresLista.get(position).getGoles()==1){
            textoGoles="gol";
        }
        holder.txtGoles.setText(Integer.toString(goleadoresLista.get(position).getGoles())+" "+textoGoles);
        //holder.logoEquipo.setImageResource();
        Picasso.get().load(goleadoresLista.get(position).getJugador().getImgUrl()).error(R.drawable.ic_player).into(holder.imgGoleador);
    }

    @Override
    public int getItemCount() {
        return goleadoresLista.size();
    }
}

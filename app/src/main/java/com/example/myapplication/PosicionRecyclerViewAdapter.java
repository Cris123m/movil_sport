package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.EquipoModel;
import com.example.myapplication.model.PosicionModel;

import java.util.List;

public class PosicionRecyclerViewAdapter extends RecyclerView.Adapter<PosicionRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView posicion,nombreEquipo,descripcionEquipo;
        private ImageView logoEquipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posicion = (TextView) itemView.findViewById(R.id.txtPosicion);
            nombreEquipo = (TextView) itemView.findViewById(R.id.txtNEquipo);
            descripcionEquipo = (TextView) itemView.findViewById(R.id.txtDEquipo);
            logoEquipo = (ImageView) itemView.findViewById(R.id.imgEquipo);
        }
    }

    public List<PosicionModel> posicionLista;

    public PosicionRecyclerViewAdapter(List<PosicionModel> posicionLista) {
        this.posicionLista = posicionLista;
    }

    @Override
    public PosicionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posicion,parent,false);
        PosicionRecyclerViewAdapter.ViewHolder viewHolder = new PosicionRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.posicion.setText(Integer.toString(posicionLista.get(position).getPuesto()));
        holder.nombreEquipo.setText(posicionLista.get(position).getEquipo().getNombre());
        holder.descripcionEquipo.setText(posicionLista.get(position).getEquipo().getDescripcion());
        //holder.logoEquipo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return posicionLista.size();
    }
}

package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.CalendarioModel;
import com.example.myapplication.model.EquipoModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class EquipoRecyclerViewAdapter extends RecyclerView.Adapter<EquipoRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreEquipo,descripcionEquipo;
        private ImageView logoEquipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEquipo = (TextView) itemView.findViewById(R.id.txtNEquipo);
            descripcionEquipo = (TextView) itemView.findViewById(R.id.txtDEquipo);
            logoEquipo = (ImageView) itemView.findViewById(R.id.imgEquipo);
        }
    }

    public List<EquipoModel> equiposLista;

    public EquipoRecyclerViewAdapter(List<EquipoModel> equiposLista) {
        this.equiposLista = equiposLista;
    }

    @Override
    public EquipoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo,parent,false);
        EquipoRecyclerViewAdapter.ViewHolder viewHolder = new EquipoRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.nombreEquipo.setText(equiposLista.get(position).getNombre());
        holder.descripcionEquipo.setText(equiposLista.get(position).getDescripcion());
        //holder.logoEquipo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return equiposLista.size();
    }
}

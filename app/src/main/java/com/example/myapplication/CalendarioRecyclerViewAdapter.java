package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.CalendarioModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CalendarioRecyclerViewAdapter extends RecyclerView.Adapter<CalendarioRecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView fecha,nombreEquipo1,descripcionEquipo1,nombreEquipo2,descripcionEquipo2;
        private ImageView logoEquipo1,logoEquipo2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = (TextView) itemView.findViewById(R.id.txtFecha);
            nombreEquipo1 = (TextView) itemView.findViewById(R.id.txtNEquipo1);
            nombreEquipo2 = (TextView) itemView.findViewById(R.id.txtNEquipo2);
            descripcionEquipo1 = (TextView) itemView.findViewById(R.id.txtDEquipo1);
            descripcionEquipo2 = (TextView) itemView.findViewById(R.id.txtDEquipo2);
            logoEquipo1 = (ImageView) itemView.findViewById(R.id.imgEquipo1);
            logoEquipo2 = (ImageView) itemView.findViewById(R.id.imgEquipo2);
        }
    }

    public List<CalendarioModel> calendarioLista;

    public CalendarioRecyclerViewAdapter(List<CalendarioModel> calendarioLista) {
        this.calendarioLista = calendarioLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendario,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        holder.fecha.setText(sdf.format(calendarioLista.get(position).getFecha()));
        holder.nombreEquipo1.setText(calendarioLista.get(position).getEquipo1().getNombre());
        holder.nombreEquipo2.setText(calendarioLista.get(position).getEquipo2().getNombre());
        holder.descripcionEquipo1.setText(calendarioLista.get(position).getEquipo1().getDescripcion());
        holder.descripcionEquipo2.setText(calendarioLista.get(position).getEquipo2().getDescripcion());
        //holder.logoEquipo1.setImageResource();
    }

    @Override
    public int getItemCount() {
        return calendarioLista.size();
    }
}
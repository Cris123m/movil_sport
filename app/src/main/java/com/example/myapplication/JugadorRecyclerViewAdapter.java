package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.JugadorModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JugadorRecyclerViewAdapter extends RecyclerView.Adapter<JugadorRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNJugador,txtNomEquipo,txtNumJugador,txtIdentificacion;
        private ImageView imgJugador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNJugador = (TextView) itemView.findViewById(R.id.txtNJugador);
            txtNomEquipo = (TextView) itemView.findViewById(R.id.txtNomEquipo);
            txtNumJugador = (TextView) itemView.findViewById(R.id.txtNumJugador);
            txtIdentificacion = (TextView) itemView.findViewById(R.id.txtIdentificacion);
            imgJugador = (ImageView) itemView.findViewById(R.id.imgJugador);
        }
    }

    public List<JugadorModel> jugadoresLista;

    public JugadorRecyclerViewAdapter(List<JugadorModel> jugadoresLista) {
        this.jugadoresLista = jugadoresLista;
    }

    @Override
    public JugadorRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador,parent,false);
        JugadorRecyclerViewAdapter.ViewHolder viewHolder = new JugadorRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNJugador.setText(jugadoresLista.get(position).getPrimerNombre() + " "
                + jugadoresLista.get(position).getSegundoNombre() + " "
                + jugadoresLista.get(position).getPrimerApellido() + " "
                + jugadoresLista.get(position).getSegundoApellido());
        holder.txtNomEquipo.setText(jugadoresLista.get(position).getEquipo().getNombre());
        holder.txtNumJugador.setText("N° Jugador: " + Integer.toString(jugadoresLista.get(position).getNumero()));
        holder.txtIdentificacion.setText("Identificación: " + jugadoresLista.get(position).getCedula());
        holder.imgJugador.setImageResource(R.drawable.ic_player);
        Picasso.get().load(jugadoresLista.get(position).getImgUrl()).error(R.drawable.ic_player).into(holder.imgJugador);
    }

    @Override
    public int getItemCount() {
        return jugadoresLista.size();
    }
}

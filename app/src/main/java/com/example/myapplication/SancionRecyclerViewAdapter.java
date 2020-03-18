package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.SancionModel;

import java.util.List;

    public class SancionRecyclerViewAdapter extends RecyclerView.Adapter<com.example.myapplication.SancionRecyclerViewAdapter.ViewHolder> {
        public static class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtNJugador,txtNomEquipo,txtNumJugador,txtSancion;
            private ImageView imgJugador;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtNJugador = (TextView) itemView.findViewById(R.id.txtNJugador);
                txtNomEquipo = (TextView) itemView.findViewById(R.id.txtNomEquipo);
                txtNumJugador = (TextView) itemView.findViewById(R.id.txtNumJugador);
                txtSancion = (TextView) itemView.findViewById(R.id.txtSancion);
                imgJugador = (ImageView) itemView.findViewById(R.id.imgJugador);
            }
        }

        public List<SancionModel> sancionLista;

        public SancionRecyclerViewAdapter(List<SancionModel> sancionLista) {
            this.sancionLista = sancionLista;
        }

        @Override
        public com.example.myapplication.SancionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sancion,parent,false);
            com.example.myapplication.SancionRecyclerViewAdapter.ViewHolder viewHolder = new com.example.myapplication.SancionRecyclerViewAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.myapplication.SancionRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.txtNJugador.setText(sancionLista.get(position).getJugador().getPrimerNombre() + " "
                                    + sancionLista.get(position).getJugador().getPrimerApellido());
            holder.txtNumJugador.setText(Integer.toString(sancionLista.get(position).getJugador().getNumero()));
            holder.txtNomEquipo.setText(sancionLista.get(position).getJugador().getEquipo().getNombre());
            holder.txtSancion.setText(sancionLista.get(position).getDetalle());

            holder.imgJugador.setImageResource(R.drawable.ic_player);
        }

        @Override
        public int getItemCount() {
            return sancionLista.size();
        }
}

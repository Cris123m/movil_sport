package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.SancionRecyclerViewAdapter;
import com.example.myapplication.SancionRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.EquipoModel;
import com.example.myapplication.model.SancionModel;
import com.example.myapplication.model.SancionModel;
import com.example.myapplication.model.JugadorModel;
import com.example.myapplication.model.SancionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SancionesFragment extends Fragment {
    View vista;
    private RecyclerView recyclerViewSancion;
    private SancionRecyclerViewAdapter adapterSancion;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_sanciones, container, false);

        recyclerViewSancion=(RecyclerView)vista.findViewById(R.id.recyclerSanciones);
        recyclerViewSancion.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarSanciones();
        //adapterSancion = new SancionRecyclerViewAdapter(obtenerSanciones());
        //recyclerViewSancion.setAdapter(adapterSancion);

        return vista;
    }

    private void listarSanciones(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<SancionModel> sanciones = obtenerSanciones(dataSnapshot);
                    adapterSancion = new SancionRecyclerViewAdapter(sanciones);
                    recyclerViewSancion.setAdapter(adapterSancion);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<SancionModel> obtenerSanciones(DataSnapshot dataSnapshot){
        List<SancionModel> sanciones = new ArrayList<>();
        for(DataSnapshot sancionSnapshot: dataSnapshot.child("sancion").getChildren()){
            if(sancionSnapshot.exists()){
                SancionModel sancion = new SancionModel();
                String idSancion = sancionSnapshot.child("idSancion").getValue().toString();
                String jugadorId = sancionSnapshot.child("jugadorId").getValue().toString();
                String detalle = sancionSnapshot.child("detalle").getValue().toString();
                sancion.setIdSancion(idSancion);
                sancion.setJugador(obtenerJugador(dataSnapshot,jugadorId));
                sancion.setDetalle(detalle);
                sanciones.add(sancion);
            }
        }

        return sanciones;
    }

    public JugadorModel obtenerJugador(DataSnapshot dataSnapshot,String jugadorId){
        JugadorModel jugador = new JugadorModel();
        DataSnapshot dsJugador = dataSnapshot.child("jugador").child(jugadorId);
        String idJugador = dsJugador.getValue().toString();
        String cedula = dsJugador.child("cedula").getValue().toString();
        String numero = dsJugador.child("numero").getValue().toString();
        String primerNombre = dsJugador.child("primerNombre").getValue().toString();
        String segundoNombre = dsJugador.child("segundoNombre").getValue().toString();
        String primerApellido = dsJugador.child("primerApellido").getValue().toString();
        String segundoApellido = dsJugador.child("segundoApellido").getValue().toString();
        String equipoId = dsJugador.child("equipoId").getValue().toString();
        String imgURL = dsJugador.child("imgURL").getValue().toString();
        jugador.setIdJugador(idJugador);
        jugador.setCedula(cedula);
        jugador.setNumero(Integer.parseInt(numero));
        jugador.setPrimerNombre(primerNombre);
        jugador.setSegundoNombre(segundoNombre);
        jugador.setPrimerApellido(primerApellido);
        jugador.setSegundoApellido(segundoApellido);
        jugador.setImgUrl(imgURL);
        //Equipos
        EquipoModel equipo = obtenerEquipo(dataSnapshot,equipoId);
        jugador.setEquipo(equipo);
        return jugador;
    }

    public EquipoModel obtenerEquipo(DataSnapshot dataSnapshot,String equipoId){
        EquipoModel equipo = new EquipoModel();
        DataSnapshot dsEquipo = dataSnapshot.child("equipo").child(equipoId);
        equipo.setIdEquipo(dsEquipo.getValue().toString());
        equipo.setNombre(dsEquipo.child("nombre").getValue().toString());
        equipo.setDescripcion(dsEquipo.child("descripcion").getValue().toString());
        return equipo;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

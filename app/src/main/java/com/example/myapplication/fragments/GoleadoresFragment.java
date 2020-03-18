package com.example.myapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EquipoRecyclerViewAdapter;
import com.example.myapplication.GoleadorRecyclerViewAdapter;
import com.example.myapplication.JugadorRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.EquipoModel;
import com.example.myapplication.model.GoleadorModel;
import com.example.myapplication.model.JugadorModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GoleadoresFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewGoleador;
    private GoleadorRecyclerViewAdapter adapterGoleador;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_goleadores, container, false);

        recyclerViewGoleador=(RecyclerView)vista.findViewById(R.id.recyclerGoleadores);
        recyclerViewGoleador.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarGoleadores();

        //adapterGoleador = new GoleadorRecyclerViewAdapter(obtenerGoleadores());
        //recyclerViewGoleador.setAdapter(adapterGoleador);

        return vista;
    }

    private void listarGoleadores(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<GoleadorModel> goleadores = obtenerGoleadores(dataSnapshot);
                    adapterGoleador = new GoleadorRecyclerViewAdapter(goleadores);
                    recyclerViewGoleador.setAdapter(adapterGoleador);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public List<GoleadorModel> obtenerGoleadores(DataSnapshot dataSnapshot){
        List<GoleadorModel> goleadores = new ArrayList<>();
        for(DataSnapshot goleadorSnapshot: dataSnapshot.child("goleador").getChildren()){
            if(goleadorSnapshot.exists()){
                GoleadorModel goleador = new GoleadorModel();
                String idGoleador = goleadorSnapshot.child("idGoleador").getValue().toString();
                String jugadorId = goleadorSnapshot.child("jugadorId").getValue().toString();
                String goles = goleadorSnapshot.child("goles").getValue().toString();
                goleador.setIdGoleador(idGoleador);
                goleador.setJugador(obtenerJugador(dataSnapshot,jugadorId));
                goleador.setGoles(Integer.parseInt(goles));
                goleadores.add(goleador);
            }
        }

        return goleadores;
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
        jugador.setIdJugador(idJugador);
        jugador.setCedula(cedula);
        jugador.setNumero(Integer.parseInt(numero));
        jugador.setPrimerNombre(primerNombre);
        jugador.setSegundoNombre(segundoNombre);
        jugador.setPrimerApellido(primerApellido);
        jugador.setSegundoApellido(segundoApellido);
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

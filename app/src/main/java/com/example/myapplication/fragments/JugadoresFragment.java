package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.JugadorRecyclerViewAdapter;
import com.example.myapplication.EquipoRecyclerViewAdapter;
import com.example.myapplication.JugadorRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.JugadorModel;
import com.example.myapplication.model.EquipoModel;
import com.example.myapplication.model.JugadorModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JugadoresFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewJugador;
    private JugadorRecyclerViewAdapter adapterJugador;

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_jugadores, container, false);

        recyclerViewJugador=(RecyclerView)vista.findViewById(R.id.recyclerJugadores);
        recyclerViewJugador.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarJugador();
        //adapterJugador = new JugadorRecyclerViewAdapter(obtenerJugadores());
        //recyclerViewJugador.setAdapter(adapterJugador);

        return vista;
    }

    private void listarJugador(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<JugadorModel> jugadores = obtenerJugadores(dataSnapshot);
                    adapterJugador = new JugadorRecyclerViewAdapter(jugadores);
                    recyclerViewJugador.setAdapter(adapterJugador);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public EquipoModel obtenerEquipo(DataSnapshot dataSnapshot,String equipoId){
        EquipoModel equipo = new EquipoModel();
        DataSnapshot dsEquipo = dataSnapshot.child("equipo").child(equipoId);
        equipo.setIdEquipo(dsEquipo.getValue().toString());
        equipo.setNombre(dsEquipo.child("nombre").getValue().toString());
        equipo.setDescripcion(dsEquipo.child("descripcion").getValue().toString());
        return equipo;
    }

    public List<JugadorModel> obtenerJugadores(DataSnapshot dataSnapshot){
        List<JugadorModel> jugadores = new ArrayList<>();
        for(DataSnapshot equipoSnapshot: dataSnapshot.child("jugador").getChildren()){
            if(equipoSnapshot.exists()){
                JugadorModel jugador = new JugadorModel();
                String idJugador = equipoSnapshot.child("idJugador").getValue().toString();
                String cedula = equipoSnapshot.child("cedula").getValue().toString();
                String numero = equipoSnapshot.child("numero").getValue().toString();
                String primerNombre = equipoSnapshot.child("primerNombre").getValue().toString();
                String segundoNombre = equipoSnapshot.child("segundoNombre").getValue().toString();
                String primerApellido = equipoSnapshot.child("primerApellido").getValue().toString();
                String segundoApellido = equipoSnapshot.child("segundoApellido").getValue().toString();
                String equipoId = equipoSnapshot.child("equipoId").getValue().toString();
                String imgURL = equipoSnapshot.child("imgURL").getValue().toString();
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
                jugadores.add(jugador);
            }
        }
        return jugadores;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

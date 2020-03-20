package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EquipoRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.EquipoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EquiposFragment extends Fragment {

    View vista;
    private RecyclerView recyclerViewEquipo;
    private EquipoRecyclerViewAdapter adapterEquipo;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_equipos, container, false);

        recyclerViewEquipo=(RecyclerView)vista.findViewById(R.id.recyclerEquipos);
        recyclerViewEquipo.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listarEquipos();

        //adapterEquipo = new EquipoRecyclerViewAdapter(obtenerEquipos());
        //recyclerViewEquipo.setAdapter(adapterEquipo);

        return vista;
    }

    private void listarEquipos(){
        mDatabase = database.getReference("equipo");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<EquipoModel> equipos = new ArrayList<>();
                for(DataSnapshot equipoSnapshot: dataSnapshot.getChildren()){
                    if(equipoSnapshot.exists()){
                        EquipoModel equipoDB = new EquipoModel();
                        String idEquipo = equipoSnapshot.child("idEquipo").getValue().toString();
                        String nombre = equipoSnapshot.child("nombre").getValue().toString();
                        String descripcion = equipoSnapshot.child("descripcion").getValue().toString();
                        String logoEquipoURL = equipoSnapshot.child("logoEquipoURL").getValue().toString();
                        equipoDB.setIdEquipo(idEquipo);
                        equipoDB.setNombre(nombre);
                        equipoDB.setDescripcion(descripcion);
                        equipoDB.setLogoEquipoURL(logoEquipoURL);
                        equipos.add(equipoDB);
                    }
                }
                adapterEquipo = new EquipoRecyclerViewAdapter(equipos);
                recyclerViewEquipo.setAdapter(adapterEquipo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*public List<EquipoModel> obtenerEquipos(){
        mDatabase = database.getReference("equipo");
        List<EquipoModel> equipos = new ArrayList<>();
        equipos.add(new EquipoModel("1","Liga","LDU"));
        equipos.add(new EquipoModel("1","Independiente del Valle","IDV"));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot equipoSnapshot: dataSnapshot.getChildren()){
                    if(dataSnapshot.exists()){
                        EquipoModel equipoDB = new EquipoModel();
                        String idEquipo = dataSnapshot.child("idEquipo").getValue().toString();
                        String nombre = dataSnapshot.child("nombre").getValue().toString();
                        String descripcion = dataSnapshot.child("descripcion").getValue().toString();
                        equipoDB.setIdEquipo(idEquipo);
                        equipoDB.setNombre(nombre);
                        equipoDB.setDescripcion(descripcion);
                        //equipos.add(equipoDB);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return equipos;
    }*/

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}

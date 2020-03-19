package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.EquipoRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.EquipoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class AgregarJugadorFragment extends Fragment {

    //Conexion con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_jugador, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AgregarJugadorFragment.this)
                        .navigate(R.id.action_AgregarJugadorFragment_to_JugadorFragment);
            }
        });
        listarEquipos();
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
                        equipoDB.setIdEquipo(idEquipo);
                        equipoDB.setNombre(nombre);
                        equipoDB.setDescripcion(descripcion);
                        equipos.add(equipoDB);
                    }
                }
                Spinner spinner = (Spinner) getView().findViewById(R.id.spEquipos);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<EquipoModel> adapter =
                        new ArrayAdapter<EquipoModel>(getContext(),android.R.layout.simple_spinner_item,equipos);
                /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.planets_array, android.R.layout.simple_spinner_item);*/
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

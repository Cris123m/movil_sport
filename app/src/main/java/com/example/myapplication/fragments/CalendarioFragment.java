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

import com.example.myapplication.CalendarioRecyclerViewAdapter;
import com.example.myapplication.EquipoRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.CalendarioModel;
import com.example.myapplication.model.EquipoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarioFragment extends Fragment {
    View vista;
    private RecyclerView recyclerViewCalendario;
    private CalendarioRecyclerViewAdapter adapterCalendario;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarioFragment newInstance(String param1, String param2) {
        CalendarioFragment fragment = new CalendarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_calendario, container, false);

        recyclerViewCalendario=(RecyclerView)vista.findViewById(R.id.recyclerCalendario);
        recyclerViewCalendario.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listarCalendario();
        //adapterCalendario = new CalendarioRecyclerViewAdapter(obtenerCalendario());
        //recyclerViewCalendario.setAdapter(adapterCalendario);

        return vista;
    }

    private void listarCalendario(){
        mDatabase = database.getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    List<CalendarioModel> calendarios = new ArrayList<>();
                    for(DataSnapshot equipoSnapshot: dataSnapshot.child("calendario").getChildren()){
                        if(equipoSnapshot.exists()){
                            CalendarioModel calendario = new CalendarioModel();
                            String idCalendario = equipoSnapshot.child("idCalendario").getValue().toString();
                            String fecha = equipoSnapshot.child("fecha").getValue().toString();
                            String idFase = equipoSnapshot.child("idFase").getValue().toString();
                            String equipo1Id = equipoSnapshot.child("equipo1Id").getValue().toString();
                            String equipo2Id = equipoSnapshot.child("equipo2Id").getValue().toString();
                            Date fechaD = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                            calendario.setIdCalendario(idCalendario);
                            calendario.setFecha(fechaD);
                            calendario.setIdFase(idFase);
                            //Equipos
                            EquipoModel equipo1 = new EquipoModel();
                            EquipoModel equipo2 = new EquipoModel();
                            DataSnapshot dsEquipo1 = dataSnapshot.child("equipo").child(equipo1Id);
                            DataSnapshot dsEquipo2 = dataSnapshot.child("equipo").child(equipo2Id);
                            equipo1.setIdEquipo(dsEquipo1.getValue().toString());
                            equipo1.setNombre(dsEquipo1.child("nombre").getValue().toString());
                            equipo1.setDescripcion(dsEquipo1.child("descripcion").getValue().toString());
                            equipo2.setIdEquipo(dsEquipo2.getValue().toString());
                            equipo2.setNombre(dsEquipo2.child("nombre").getValue().toString());
                            equipo2.setDescripcion(dsEquipo2.child("descripcion").getValue().toString());
                            calendario.setEquipo1(equipo1);
                            calendario.setEquipo2(equipo2);
                            calendarios.add(calendario);
                        }
                    }
                    adapterCalendario = new CalendarioRecyclerViewAdapter(calendarios);
                    recyclerViewCalendario.setAdapter(adapterCalendario);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<CalendarioModel> obtenerCalendario(){
        List<CalendarioModel> calendario = new ArrayList<>();
        EquipoModel equipo1 = new EquipoModel("1","Liga","LDU");
        EquipoModel equipo2 = new EquipoModel("1","Independiente del Valle","IDV");
        calendario.add(new CalendarioModel("1",new Date(),"Primera",equipo1,equipo2));
        calendario.add(new CalendarioModel("2",new Date(),"Segunda",equipo2,equipo1));

        return calendario;
    }
}

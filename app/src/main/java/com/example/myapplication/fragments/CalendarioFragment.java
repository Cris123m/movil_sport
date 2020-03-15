package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CalendarioRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.CalendarioModel;
import com.example.myapplication.model.EquipoModel;

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

        adapterCalendario = new CalendarioRecyclerViewAdapter(obtenerCalendario());
        recyclerViewCalendario.setAdapter(adapterCalendario);

        return vista;
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
package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.IComunicaFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Activity actividad;
    CardView cardHistoria,cardCalendario,cardEquipos,cardPosiciones,cardGoleadores,cardSanciones,cardJugadores,cardEstadios,cardSalir;
    IComunicaFragment interfaceComunicaFragment;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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
        vista=inflater.inflate(R.layout.fragment_inicio, container, false);

        cardHistoria=vista.findViewById(R.id.cardHistoria);
        cardCalendario=vista.findViewById(R.id.cardCalendario);
        cardEquipos=vista.findViewById(R.id.cardEquipos);
        cardPosiciones=vista.findViewById(R.id.cardPosiciones);
        cardGoleadores=vista.findViewById(R.id.cardGoleadores);
        cardSanciones=vista.findViewById(R.id.cardSanciones);
        cardJugadores=vista.findViewById(R.id.cardJugadores);
        cardEstadios=vista.findViewById(R.id.cardEstadios);
        cardSalir=vista.findViewById(R.id.cardSalir);

        eventosMenu();

        // Inflate the layout for this fragment
        return vista;
    }

    private void eventosMenu(){
        cardHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verHistoria();
            }
        });

        cardCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verCalendario();
            }
        });

        cardEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verEquipos();
            }
        });

        cardPosiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verTablaPosiciones();
            }
        });

        cardGoleadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verGoleadores();
            }
        });

        cardSanciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verSanciones();
            }
        });

        cardJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verJugadores();
            }
        });

        cardEstadios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.verEstadios();
            }
        });

        cardSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicaFragment.Salir();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad = (Activity) context;
            interfaceComunicaFragment = (IComunicaFragment) actividad;
        }
    }
}

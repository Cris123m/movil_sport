package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.fragments.InicioFragment;
import com.example.myapplication.interfaces.IComunicaFragment;

public class MenuActivity extends AppCompatActivity implements IComunicaFragment {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    Fragment fragmentInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fragmentInicio = new InicioFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    @Override
    public void verHistoria() {
        Intent intent = new Intent(this,HistoriaActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verCalendario() {
        Intent intent = new Intent(this,CalendarioActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verEquipos() {
        Intent intent = new Intent(this,EquiposActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verTablaPosiciones() {
        Intent intent = new Intent(this,PosicionesActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verGoleadores() {
        Intent intent = new Intent(this,GoleadoresActivity.class);
        intent.putExtra("idMenu",5);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verSanciones() {
        Intent intent = new Intent(this,GoleadoresActivity.class);
        intent.putExtra("idMenu",6);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verJugadores() {
        Intent intent = new Intent(this,GoleadoresActivity.class);
        intent.putExtra("idMenu",7);
        startActivityForResult(intent, 0);
    }

    @Override
    public void Salir() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("idMenu",false);
        startActivityForResult(intent, 0);
    }
}

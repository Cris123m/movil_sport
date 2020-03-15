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
        Toast.makeText(this, "Historia", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,HistoriaActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verCalendario() {
        Toast.makeText(this, "Ver calendario desde activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CalendarioActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void verEquipos() {
        Toast.makeText(this, "Ver equipos desde activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void verTablaPosiciones() {

    }

    @Override
    public void verGoleadores() {

    }

    @Override
    public void verSanciones() {

    }

    @Override
    public void verJugadores() {

    }

    @Override
    public void Salir() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivityForResult(intent, 0);
    }
}

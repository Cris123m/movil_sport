package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.fragments.AgregarJugadorFragment;
import com.example.myapplication.fragments.JugadoresFragment;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class GoleadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        final int idMenu = extras.getInt("idMenu");
        switch (idMenu){
            case 5:
                setContentView(R.layout.activity_goleadores);
                this.setTitle("Goleadores");
                break;
            case 6:
                setContentView(R.layout.activity_sanciones);
                this.setTitle("Sanciones");
                break;
            case 7:
                setContentView(R.layout.activity_jugadores);
                this.setTitle("Jugadores");
                break;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*switch (idMenu){
                    case 7:
                        AgregarJugadorFragment agregarJugadorFragment = new AgregarJugadorFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment,agregarJugadorFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }*/
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.borrar:
                Toast.makeText(this, "Ha presionado borrar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editar:
                Toast.makeText(this, "Ha presionado editar", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.addToDictionary:
                Toast.makeText(this, "Ha presionado agregar", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

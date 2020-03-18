package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class GoleadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        int idMenu = extras.getInt("idMenu");
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
    }

}

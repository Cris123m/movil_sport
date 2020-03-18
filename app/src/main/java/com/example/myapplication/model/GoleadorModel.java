package com.example.myapplication.model;

public class GoleadorModel {
    private String idGoleador;
    private JugadorModel jugador;
    private int goles;

    public GoleadorModel() {
    }

    public GoleadorModel(String idGoleador, JugadorModel jugador, int goles) {
        this.idGoleador = idGoleador;
        this.jugador = jugador;
        this.goles = goles;
    }

    public String getIdGoleador() {
        return idGoleador;
    }

    public void setIdGoleador(String idGoleador) {
        this.idGoleador = idGoleador;
    }

    public JugadorModel getJugador() {
        return jugador;
    }

    public void setJugador(JugadorModel jugador) {
        this.jugador = jugador;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }
}

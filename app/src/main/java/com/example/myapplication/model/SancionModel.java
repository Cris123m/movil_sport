package com.example.myapplication.model;

public class SancionModel {
    private String idSancion;
    private JugadorModel jugador;
    private  String detalle;

    public SancionModel() {
    }

    public SancionModel(String idSancion, JugadorModel jugador, String detalle) {
        this.idSancion = idSancion;
        this.jugador = jugador;
        this.detalle = detalle;
    }

    public String getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(String idSancion) {
        this.idSancion = idSancion;
    }

    public JugadorModel getJugador() {
        return jugador;
    }

    public void setJugador(JugadorModel jugador) {
        this.jugador = jugador;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}

package com.example.myapplication.model;

public class PosicionModel {
    private String idPosicion;
    private int puesto;
    private EquipoModel equipo;

    public PosicionModel() {
    }

    public PosicionModel(String idPosicion, int puesto, EquipoModel equipo) {
        this.idPosicion = idPosicion;
        this.puesto = puesto;
        this.equipo = equipo;
    }

    public String getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(String idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public EquipoModel getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoModel equipo) {
        this.equipo = equipo;
    }
}

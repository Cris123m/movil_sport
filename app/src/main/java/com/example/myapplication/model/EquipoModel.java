package com.example.myapplication.model;

public class EquipoModel {
    private String idEquipo;
    private String nombre;
    private String descripcion;

    public EquipoModel() {
    }

    public EquipoModel(String idEquipo, String nombre, String descripcion) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

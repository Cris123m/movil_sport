package com.example.myapplication.model;

import java.util.Date;

public class CalendarioModel {
    private String idCalendario;
    private Date fecha;
    private String idFase;
    private EquipoModel equipo1;
    private EquipoModel equipo2;

    public CalendarioModel() {
    }

    public CalendarioModel(String idCalendario, Date fecha, String idFase, EquipoModel equipo1, EquipoModel equipo2) {
        this.idCalendario = idCalendario;
        this.fecha = fecha;
        this.idFase = idFase;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public String getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdFase() {
        return idFase;
    }

    public void setIdFase(String idFase) {
        this.idFase = idFase;
    }

    public EquipoModel getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(EquipoModel equipo1) {
        this.equipo1 = equipo1;
    }

    public EquipoModel getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(EquipoModel equipo2) {
        this.equipo2 = equipo2;
    }
}

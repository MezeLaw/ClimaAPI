package com.mz.ClimaAPI.dto;

public class PlanetaDto {

    private String nombre;
    private double coordenadaX;
    private double coordenadaY;
    private int anguloEnGrados;
    private int dia;
    private int distanciaAlSol;
    private String clima;

    public PlanetaDto(){

    }

    public PlanetaDto(String nombre, double coordenadaX, double coordenadaY, int anguloEnGrados, int dia, int distanciaAlSol, String clima) {
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.anguloEnGrados = anguloEnGrados;
        this.dia = dia;
        this.distanciaAlSol = distanciaAlSol;
        this.clima = clima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public int getAnguloEnGrados() {
        return anguloEnGrados;
    }

    public void setAnguloEnGrados(int anguloEnGrados) {
        this.anguloEnGrados = anguloEnGrados;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getDistanciaAlSol() {
        return distanciaAlSol;
    }

    public void setDistanciaAlSol(int distanciaAlSol) {
        this.distanciaAlSol = distanciaAlSol;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}

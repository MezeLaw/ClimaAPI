package com.mz.ClimaAPI.dto;

public class ClimaResponseDto {

    private int dia;
    private String clima;

    public ClimaResponseDto(int dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public ClimaResponseDto() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

}

package com.mz.ClimaAPI.dto;

public class PlanetaInfoDto {

    /**
     *
     *  Retorna los resultados de los planetas en un 'n' dia.
     *
     * */

    private PlanetaDto vulcano;
    private PlanetaDto betasoide;
    private PlanetaDto ferengis;
    private double perimetro;

    public PlanetaInfoDto() {

    }

    public PlanetaInfoDto(PlanetaDto vulcano, PlanetaDto betasoide, PlanetaDto ferengis) {
        this.vulcano = vulcano;
        this.betasoide = betasoide;
        this.ferengis = ferengis;
    }

    public PlanetaDto getVulcano() {
        return vulcano;
    }

    public void setVulcano(PlanetaDto vulcano) {
        this.vulcano = vulcano;
    }

    public PlanetaDto getBetasoide() {
        return betasoide;
    }

    public void setBetasoide(PlanetaDto betasoide) {
        this.betasoide = betasoide;
    }

    public PlanetaDto getFerengis() {
        return ferengis;
    }

    public void setFerengis(PlanetaDto ferengis) {
        this.ferengis = ferengis;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }
}

package com.mz.ClimaAPI.dto;

public class ClimaDecadaDto {

    private int periodosDeSequia;
    private int periodosDeLluvia;
    private int periodosDeLluviaMaxima;
    private int periodosDeCondicionesOptimas;

    public ClimaDecadaDto(){

    }

    public ClimaDecadaDto(int periodosDeSequia, int periodosDeLluvia, int periodosDeLluviaMaxima, int periodosDeCondicionesOptimas) {
        this.periodosDeSequia = periodosDeSequia;
        this.periodosDeLluvia = periodosDeLluvia;
        this.periodosDeLluviaMaxima = periodosDeLluviaMaxima;
        this.periodosDeCondicionesOptimas = periodosDeCondicionesOptimas;
    }

    public int getPeriodosDeSequia() {
        return periodosDeSequia;
    }

    public void setPeriodosDeSequia(int periodosDeSequia) {
        this.periodosDeSequia = periodosDeSequia;
    }

    public int getPeriodosDeLluvia() {
        return periodosDeLluvia;
    }

    public void setPeriodosDeLluvia(int periodosDeLluvia) {
        this.periodosDeLluvia = periodosDeLluvia;
    }

    public int getPeriodosDeLluviaMaxima() {
        return periodosDeLluviaMaxima;
    }

    public void setPeriodosDeLluviaMaxima(int periodosDeLluviaMaxima) {
        this.periodosDeLluviaMaxima = periodosDeLluviaMaxima;
    }

    public int getPeriodosDeCondicionesOptimas() {
        return periodosDeCondicionesOptimas;
    }

    public void setPeriodosDeCondicionesOptimas(int periodosDeCondicionesOptimas) {
        this.periodosDeCondicionesOptimas = periodosDeCondicionesOptimas;
    }
}

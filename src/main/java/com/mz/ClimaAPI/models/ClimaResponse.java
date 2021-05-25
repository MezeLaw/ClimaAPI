package com.mz.ClimaAPI.models;

import javax.persistence.*;

@Entity
@Table(schema = "public", name="clima")
public class ClimaResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    private int dia;

    @Column
    private String clima;


    public ClimaResponse(Long id, int dia, String clima) {
        this.id = id;
        this.dia = dia;
        this.clima = clima;
    }

    public ClimaResponse(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

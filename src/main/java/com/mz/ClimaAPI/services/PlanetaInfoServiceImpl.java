package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.dto.PlanetaDto;
import com.mz.ClimaAPI.dto.PlanetaInfoDto;
import org.springframework.stereotype.Service;

@Service
public class PlanetaInfoServiceImpl implements PlanetaInfoService {
    @Override
    public PlanetaInfoDto obtenerPosicionesPlanetas(int dia) {

        PlanetaInfoDto planetaInfoDto = new PlanetaInfoDto();

        PlanetaDto vulcano = new PlanetaDto("Vulcano", 0.0, 0.0, 0, 0, 1000, "Undefined");
        PlanetaDto betasoide = new PlanetaDto("Betasoide", 0.0, 0.0, 0, 0, 2000, "Undefined");
        PlanetaDto ferengis = new PlanetaDto("Ferengis", 0.0, 0.0, 0, 0, 500, "Undefined");

        // Calculo los grados correspondientes y actualizo las coordenadas con trigonometría.
          // Actualizo angulos en grados segun dia
        ferengis.setAnguloEnGrados(dia);
        betasoide.setAnguloEnGrados(3*dia);
        vulcano.setAnguloEnGrados(-5*dia);

        //Calculo nuevas coordenadas
        ferengis.setCoordenadaX(Math.cos(Math.toRadians(ferengis.getAnguloEnGrados())) * ferengis.getDistanciaAlSol());
        ferengis.setCoordenadaY(Math.sin(Math.toRadians(ferengis.getAnguloEnGrados())) * ferengis.getDistanciaAlSol());

        betasoide.setCoordenadaX(Math.cos(Math.toRadians(betasoide.getAnguloEnGrados())) * betasoide.getDistanciaAlSol());
        betasoide.setCoordenadaY(Math.sin(Math.toRadians(betasoide.getAnguloEnGrados())) * betasoide.getDistanciaAlSol());

        vulcano.setCoordenadaX(Math.cos(Math.toRadians(vulcano.getAnguloEnGrados())) * vulcano.getDistanciaAlSol());
        vulcano.setCoordenadaY(Math.sin(Math.toRadians(vulcano.getAnguloEnGrados())) * vulcano.getDistanciaAlSol());

        planetaInfoDto.setBetasoide(betasoide);
        planetaInfoDto.setFerengis(ferengis);
        planetaInfoDto.setVulcano(vulcano);

        return planetaInfoDto;
    }

    @Override
    public ClimaResponseDto obtenerClimaGalaxia(PlanetaInfoDto planetaInfoDto) {

        ClimaResponseDto climaResponseDto = new ClimaResponseDto(planetaInfoDto.getBetasoide().getDia(), "Undefined");

        //En este motodo debo devolver el clima del dia, por lo tanto debo averiguar si son colineales entre si y/o con el sol,
        //Alineados entre si y con el sol => Sequia
        //Alineados entre si solamente => Condiciones optimas de temp y presion
        //Triangulo con sol incluido es lluvia, siendo maxima intensidad cuando el perimetro es maximo
        //Triangulo sin sol en el centro => undefined

        boolean planetasAlineados = planetaInfoDto.getFerengis().getCoordenadaY() - planetaInfoDto.getBetasoide().getCoordenadaY() / planetaInfoDto.getFerengis().getCoordenadaX() - planetaInfoDto.getBetasoide().getCoordenadaX() == planetaInfoDto.getVulcano().getCoordenadaY() - planetaInfoDto.getBetasoide().getCoordenadaY() / planetaInfoDto.getVulcano().getCoordenadaX() - planetaInfoDto.getBetasoide().getCoordenadaX();
        boolean planetasAlineadosConSol = planetaInfoDto.getFerengis().getCoordenadaY() - 0.0 / planetaInfoDto.getFerengis().getCoordenadaX() - 0.0 == planetaInfoDto.getVulcano().getCoordenadaY() - 0.0 / planetaInfoDto.getVulcano().getCoordenadaX() - 0.0;



        if(planetasAlineados) {
            planetaInfoDto.getVulcano().setClima("Condiciones optimas");
            planetaInfoDto.getBetasoide().setClima("Condiciones optimas");
            planetaInfoDto.getFerengis().setClima("Condiciones optimas");

            climaResponseDto.setClima("Condiciones optimas");
            climaResponseDto.setDia(planetaInfoDto.getBetasoide().getDia());
        } else if(planetasAlineados && planetasAlineadosConSol){
            planetaInfoDto.getVulcano().setClima("Sequia");
            planetaInfoDto.getBetasoide().setClima("Sequia");
            planetaInfoDto.getFerengis().setClima("Sequia");

            climaResponseDto.setClima("Sequia");
            climaResponseDto.setDia(planetaInfoDto.getBetasoide().getDia());
        }

        double perimetro = this.getPerimetro(planetaInfoDto.getBetasoide(), planetaInfoDto.getFerengis(), planetaInfoDto.getVulcano());

        boolean solContenido = this.solContenido(planetaInfoDto.getBetasoide(), planetaInfoDto.getFerengis(), planetaInfoDto.getVulcano());

        //Evaluo posicion del sol o no para determinar lluvia.

        //Evaluo si es max perimetro para determinar lluvia pico


        //

        return  climaResponseDto;
    }

    public double getPerimetro(PlanetaDto betasoide, PlanetaDto ferengis, PlanetaDto vulcano){

           /**
            *  Recibe los 3 planetas y retorna el perimetro correspondiente
            * */

        return 1;
    }

    public boolean solContenido(PlanetaDto betasoide, PlanetaDto ferengis, PlanetaDto vulcano){
        /**
         *  Retorna si el sol esta dentro del triangulo formado por los 3 planetas o no.
         * */


        return  false;
    }

    public PlanetaInfoDto setClima(PlanetaInfoDto){

        return null;
    }
}


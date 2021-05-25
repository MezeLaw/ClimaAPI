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

        PlanetaDto vulcano = new PlanetaDto("Vulcano", 0.0, 0.0, 0, dia, 1000, "Undefined");
        PlanetaDto betasoide = new PlanetaDto("Betasoide", 0.0, 0.0, 0, dia, 2000, "Undefined");
        PlanetaDto ferengis = new PlanetaDto("Ferengis", 0.0, 0.0, 0, dia, 500, "Undefined");


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

        if(planetasAlineados && !planetasAlineadosConSol){

            System.out.println("Planetas alineados>>>"+ planetasAlineados);
        }

        if(planetasAlineados && planetasAlineadosConSol) {

            planetaInfoDto.getVulcano().setClima("Sequia");
            planetaInfoDto.getBetasoide().setClima("Sequia");
            planetaInfoDto.getFerengis().setClima("Sequia");
            climaResponseDto.setClima("Sequia");

            climaResponseDto.setDia(planetaInfoDto.getBetasoide().getDia());
        } else if(planetasAlineados && !planetasAlineadosConSol){

            planetaInfoDto.getVulcano().setClima("Condiciones optimas");
            planetaInfoDto.getBetasoide().setClima("Condiciones optimas");
            planetaInfoDto.getFerengis().setClima("Condiciones optimas");
            climaResponseDto.setClima("Condiciones optimas");

            climaResponseDto.setDia(planetaInfoDto.getBetasoide().getDia());
        } else {

            //Evaluo posicion del sol o no para determinar lluvia.
            boolean solContenido = this.solContenido(planetaInfoDto.getBetasoide(), planetaInfoDto.getFerengis(), planetaInfoDto.getVulcano());

            if(solContenido) {

                planetaInfoDto.getVulcano().setClima("Lluvia");
                planetaInfoDto.getBetasoide().setClima("Lluvia");
                planetaInfoDto.getFerengis().setClima("Lluvia");
                climaResponseDto.setDia(planetaInfoDto.getBetasoide().getDia());
                climaResponseDto.setClima("Lluvia");
                //Evaluo perimetro para mas adelante analizar el maximo
                planetaInfoDto.setPerimetro(this.getPerimetro(planetaInfoDto.getBetasoide(), planetaInfoDto.getFerengis(), planetaInfoDto.getVulcano()));
            }

        }
        return  climaResponseDto;
    }


    /**
     *  Recibe los 3 planetas y retorna el perimetro correspondiente
     * */
    public double getPerimetro(PlanetaDto betasoide, PlanetaDto ferengis, PlanetaDto vulcano){

        double ladoBetasoideFerengis = Math.abs(Math.sqrt(Math.pow((ferengis.getCoordenadaX() - betasoide.getCoordenadaX()), 2)) + Math.pow(ferengis.getCoordenadaY()-betasoide.getCoordenadaY(),2));
        double ladoBetaSoideVulcano = Math.abs(Math.sqrt(Math.pow((vulcano.getCoordenadaX() - betasoide.getCoordenadaX()), 2)) + Math.pow(vulcano.getCoordenadaY()-betasoide.getCoordenadaY(),2));
        double ladoVulcanoFerengis = Math.abs(Math.sqrt(Math.pow((ferengis.getCoordenadaX() - vulcano.getCoordenadaX()), 2)) + Math.pow(ferengis.getCoordenadaY()-vulcano.getCoordenadaY(),2));

        return ladoVulcanoFerengis+ladoBetasoideFerengis+ladoBetaSoideVulcano;
    }


    /**
     *  Retorna si el sol esta dentro del triangulo formado por los 3 planetas o no.
     *
     *  Algoritmia investigada de https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
     *
     * */

    public boolean solContenido(PlanetaDto betasoide, PlanetaDto ferengis, PlanetaDto vulcano){


        /* Calculate area of triangle ABC */
        double A = area (betasoide.getCoordenadaX(), betasoide.getCoordenadaY(), ferengis.getCoordenadaX(), ferengis.getCoordenadaY(), vulcano.getCoordenadaX(), vulcano.getCoordenadaY());

        /* Calculate area of triangle PBC */
        double A1 = area (0, 0, ferengis.getCoordenadaX(), ferengis.getCoordenadaY(), vulcano.getCoordenadaX(), vulcano.getCoordenadaY());

        /* Calculate area of triangle PAC */
        double A2 = area (betasoide.getCoordenadaX(), betasoide.getCoordenadaY(), 0, 0, vulcano.getCoordenadaX(), vulcano.getCoordenadaY());

        /* Calculate area of triangle PAB */
        double A3 = area (betasoide.getCoordenadaX(), betasoide.getCoordenadaY(), ferengis.getCoordenadaX(), ferengis.getCoordenadaY(), 0, 0);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);

    }


    /* A utility function to calculate area of triangle
       formed by (x1, y1) (x2, y2) and (x3, y3) */
    static double area(double x1, double y1, double x2, double y2,
                       double x3, double y3) {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+
                x3*(y1-y2))/2.0);
    }

}


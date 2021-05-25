package com.mz.ClimaAPI.controllers;

import com.google.gson.Gson;
import com.mz.ClimaAPI.models.ClimaResponse;
import com.mz.ClimaAPI.repositories.ClimaRepository;
import com.mz.ClimaAPI.services.ClimaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class ClimaController {

    @Autowired
    private ClimaRepository climaRepository;

    @Autowired
    private ClimaServiceImpl climaService;

    private Gson gson = new Gson();

    @GetMapping("healthcheck")
    public String healthcheck(){
        return "<h1>Clima controller works :)  </h1>";
    }


    @GetMapping("clima")
    @ResponseBody
    public String climaPorDia(@RequestParam int dia){
        return gson.toJson(this.climaService.obtenerClimaPorDia(dia));
    }

    /**
     *  Este seria el endpoint que genera los registros para consultarlos luego.
     *
     *  Devuelve ok o error de acuerdo al resultado de la ejecucion del job que luego impactara en la db,
     *  para poder ser consultado dia a dia por el metodo anterior /clima/dia
     *
     * */
    @GetMapping("clima/calcularDiezAnos")
    @ResponseBody
    public ResponseEntity<String> calcularRegistrosDiezAnos(){


        ArrayList<ClimaResponse> climaResponseEntities = this.climaService.obtenerClimaDeDiezAnos();

        try {
            this.climaRepository.saveAll(climaResponseEntities);
            System.out.println("Se ejecuto correctamente el Job para generar el clima durante los proximos 10 años.");

            return new ResponseEntity<String>("La ejecucion del job fue exitosa.", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al intentar ejecutar el job.");
            return new ResponseEntity<String>("Se produjeron errores al intentar realizar la ejecucion del job.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     *  Retorna estadisticas de 10 años en adelante.
     *
     * */

    @GetMapping("clima/resultadosDiezAnos")
    @ResponseBody
    public String obtenerResultadosDiezAnos(){
        return null;
    }



}

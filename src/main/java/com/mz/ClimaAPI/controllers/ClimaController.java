package com.mz.ClimaAPI.controllers;

import com.google.gson.Gson;
import com.mz.ClimaAPI.dto.ClimaDecadaDto;
import com.mz.ClimaAPI.models.Clima;
import com.mz.ClimaAPI.repositories.ClimaRepository;
import com.mz.ClimaAPI.services.ClimaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<String> climaPorDia(@RequestParam int dia){

        try {
            Clima clima = this.climaRepository.findByDia(dia);


            if(clima == null ){
                return new ResponseEntity<String>("No se encontraron resultados para el dia indicado.", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(gson.toJson(clima), HttpStatus.OK);
            }

        } catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<String>("Ocurrio un error al intentar realizar la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *  Este seria el endpoint que genera los registros para consultarlos luego.
     *
     *  Devuelve ok o error de acuerdo al resultado de la ejecucion del job que luego impactara en la db,
     *  para poder ser consultado dia a dia por el metodo anterior /clima/dia
     *
     * */
    @PostMapping("clima/calcularDiezAnos")
    @ResponseBody
    public ResponseEntity<String> calcularRegistrosDiezAnos(){

        //Delete de seguridad para no seguir pisando la DB...
        this.climaRepository.deleteAll();
        ArrayList<Clima> climaEntities = this.climaService.obtenerClimaDeDiezAnos();

        try {
            this.climaRepository.saveAll(climaEntities);

            return new ResponseEntity<String>("La ejecucion del job fue exitosa.", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
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
    public ResponseEntity<String> obtenerResultadosDiezAnos(){

        try {

            ClimaDecadaDto climaDecadaDto = new ClimaDecadaDto();

            List<Clima> climaResults = (List<Clima>) climaRepository.findAll();

            int sequias = (int) climaResults.stream().filter(p -> p.getClima().equals("Sequia")).count();
            int lluvias = (int) climaResults.stream().filter(p -> p.getClima().equals("Lluvia")).count();
            int lluviasPico = (int) climaResults.stream().filter(p -> p.getClima().equals("Lluvia maxima")).count();
            int condicionesOptimas = (int) climaResults.stream().filter(p -> p.getClima().equals("Condiciones optimas")).count();



            climaDecadaDto.setPeriodosDeLluvia(lluvias);
            climaDecadaDto.setPeriodosDeLluviaMaxima(lluviasPico);
            climaDecadaDto.setPeriodosDeSequia(sequias);
            climaDecadaDto.setPeriodosDeCondicionesOptimas(condicionesOptimas);

            return new ResponseEntity<String>( gson.toJson(climaDecadaDto), HttpStatus.OK);


        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>( "Ocurrio un error al intentar realizar la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}

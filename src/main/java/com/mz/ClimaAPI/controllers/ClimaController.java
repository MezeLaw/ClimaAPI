package com.mz.ClimaAPI.controllers;

import com.google.gson.Gson;
import com.mz.ClimaAPI.services.ClimaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ClimaController {

    @Autowired
    ClimaServiceImpl climaService;

    private Gson gson;

    @GetMapping("healthcheck")
    public String healthcheck(){
        return "<h1>Clima controller works :)  </h1>";
    }

    @GetMapping("clima")
    @ResponseBody
    public String climaPorDia(@RequestParam int dia){

        return gson.toJson(this.climaService.obtenerClimaPorDia(dia));
    }

}

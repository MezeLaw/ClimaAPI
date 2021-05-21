package com.mz.ClimaAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ClimaController {

    @GetMapping("healthcheck")
    public String healthcheck(){
        return "<h1>Clima controller works :)  </h1>";
    }

}

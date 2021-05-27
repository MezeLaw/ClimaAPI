package com.mz.ClimaAPI;

import com.mz.ClimaAPI.controllers.ClimaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ControllerTests {

    @Autowired
    private ClimaController climaController;

    @Test
    public void climaPorDiaTestReturnsOk() throws Exception {

        ResponseEntity<String> response = this.climaController.climaPorDia(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void climaPorDiaTestReturnsMessageBodyAndOk() throws Exception {


        ResponseEntity<String> response = this.climaController.climaPorDia(99789);
        assertEquals("No se encontraron resultados para el dia indicado.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}

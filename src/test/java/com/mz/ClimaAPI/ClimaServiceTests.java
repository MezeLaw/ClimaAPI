package com.mz.ClimaAPI;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.models.Clima;
import com.mz.ClimaAPI.services.ClimaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClimaServiceTests {


    @Autowired
    ClimaServiceImpl climaService;


    @Test
    public void obtenerClimaDeDiezAnosRetorna3650RegistrosYNoEsNulo(){

        ArrayList<Clima> diezAnosResults = this.climaService.obtenerClimaDeDiezAnos();

        diezAnosResults.stream().map(Clima::getDia)
                .filter( c -> c != -1).count();

        assertNotNull(diezAnosResults);
        assertEquals(3650, diezAnosResults.stream().map(Clima::getDia)
                .filter( c -> c != null).count());

    }

    @Test
    public void elPrimerDiaEsSequia(){
        ClimaResponseDto climaResponseDto = this.climaService.calcularClimaPorDia(0);
        assertEquals(climaResponseDto.getClima(), "Sequia");
    }

}

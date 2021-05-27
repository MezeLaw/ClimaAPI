package com.mz.ClimaAPI;

import com.mz.ClimaAPI.models.Clima;
import com.mz.ClimaAPI.repositories.ClimaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClimaRepositoryTests {

    @Autowired
    private ClimaRepository climaRepository;

    @Test
    public void climaDiaCeroRetornaDistintoDeNull(){

        Clima clima = this.climaRepository.findByDia(0);
        assertNotNull(clima);
    }

    @Test
    public void climaDiaInvalidoReturnNull(){
        Clima clima = this.climaRepository.findByDia(9999);
        assertEquals(clima, null);
    }
}

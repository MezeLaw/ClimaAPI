package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.dto.PlanetaInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClimaServiceImpl implements ClimaService {

    @Autowired
    private PlanetaInfoServiceImpl planetaInfoService;

    @Override
    public ClimaResponseDto obtenerClimaPorDia(int dia) {

        PlanetaInfoDto planetaInfoDto = this.planetaInfoService.obtenerPosicionesPlanetas(dia);

        ClimaResponseDto climaResponseDto = this.planetaInfoService.obtenerClimaGalaxia(planetaInfoDto);

        return climaResponseDto;
    }

    @Override
    public ArrayList<ClimaResponseDto> obtenerClimaDeDiezAnos() {



        return null;
    }
}

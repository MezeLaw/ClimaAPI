package com.mz.ClimaAPI.services;

import com.google.gson.Gson;
import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.dto.PlanetaInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClimaServiceImpl implements ClimaService {

    @Autowired
    private PlanetaInfoServiceImpl planetaInfoService;

    private Gson gson = new Gson();

    @Override
    public ClimaResponseDto obtenerClimaPorDia(int dia) {

        PlanetaInfoDto planetaInfoDto = this.planetaInfoService.obtenerPosicionesPlanetas(dia);

        ClimaResponseDto climaResponseDto = this.planetaInfoService.obtenerClimaGalaxia(planetaInfoDto);

        return climaResponseDto;
    }

    @Override
    public ArrayList<ClimaResponseDto> obtenerClimaDeDiezAnos() {

        ArrayList<ClimaResponseDto> resultadosDiezAnos = new ArrayList<ClimaResponseDto>();
        ArrayList<PlanetaInfoDto> planetaListAux = new ArrayList<PlanetaInfoDto>();

        for(int i=0; i<3650; i++){
            PlanetaInfoDto planetaInfoDto = this.planetaInfoService.obtenerPosicionesPlanetas(i);
            ClimaResponseDto climaResponseDto = this.planetaInfoService.obtenerClimaGalaxia(planetaInfoDto);
            planetaListAux.add(planetaInfoDto);
            resultadosDiezAnos.add(climaResponseDto);
        }

        PlanetaInfoDto planetaInfoDtoMaxPerimetro = planetaListAux
                .stream()
                .max(Comparator.comparing(PlanetaInfoDto::getPerimetro))
                .orElseThrow(NoSuchElementException::new);

        System.out.print("Info del perimetro maximo ---> "+ gson.toJson(planetaInfoDtoMaxPerimetro));

        boolean found = false;
        for(int i = 0; i< resultadosDiezAnos.size() && !found; i++){
            if(resultadosDiezAnos.get(i).getDia()==planetaInfoDtoMaxPerimetro.getBetasoide().getDia()){
                resultadosDiezAnos.get(i).setClima("Lluvia maxima.");
                found = true;
            }
        }

        return resultadosDiezAnos;
    }
}

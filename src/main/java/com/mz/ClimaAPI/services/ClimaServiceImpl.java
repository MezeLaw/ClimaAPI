package com.mz.ClimaAPI.services;

import com.google.gson.Gson;
import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.dto.PlanetaInfoDto;
import com.mz.ClimaAPI.models.Clima;
import com.mz.ClimaAPI.repositories.ClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClimaServiceImpl implements ClimaService {


    @Autowired
    private ClimaRepository climaRepository;

    @Autowired
    private PlanetaInfoServiceImpl planetaInfoService;

    private Gson gson = new Gson();

    @Override
    public ClimaResponseDto calcularClimaPorDia(int dia) {

        PlanetaInfoDto planetaInfoDto = this.planetaInfoService.obtenerPosicionesPlanetas(dia);

        ClimaResponseDto climaResponseDto = this.planetaInfoService.obtenerClimaGalaxia(planetaInfoDto);

        return climaResponseDto;
    }

    @Override
    public ArrayList<Clima> obtenerClimaDeDiezAnos() {

        ArrayList<ClimaResponseDto> resultadosDiezAnos = new ArrayList<ClimaResponseDto>();
        ArrayList<PlanetaInfoDto> planetaListAux = new ArrayList<PlanetaInfoDto>();

        ArrayList<Clima> climaListEntity = new ArrayList<Clima>();

        for(int i=0; i<3650; i++){
            PlanetaInfoDto planetaInfoDto = this.planetaInfoService.obtenerPosicionesPlanetas(i);
            ClimaResponseDto climaResponseDto = this.planetaInfoService.obtenerClimaGalaxia(planetaInfoDto);
            planetaListAux.add(planetaInfoDto);
            resultadosDiezAnos.add(climaResponseDto);
            Clima climaEntity = new Clima();
            climaEntity.setDia(climaResponseDto.getDia());
            climaEntity.setClima(climaResponseDto.getClima());
            climaListEntity.add(climaEntity);

        }

        PlanetaInfoDto planetaInfoDtoMaxPerimetro = planetaListAux
                .stream()
                .max(Comparator.comparing(PlanetaInfoDto::getPerimetro))
                .orElseThrow(NoSuchElementException::new);


        /**
         *
         *       List<ClimaResponseDto> listaLoca =
         *                 resultadosDiezAnos.stream()
         *                         .filter(elemDelFilter -> elemDelFilter.getDia() == 0 || elemDelFilter.getDia() != 0)
         *                         .map(elemDelMap -> elemDelMap.setClima("xD"))
         *                         .Collections.asList();
         *
         */

        boolean found = false;
        for(int i = 0; i< climaListEntity.size() && !found; i++){
            if(climaListEntity.get(i).getDia()==planetaInfoDtoMaxPerimetro.getBetasoide().getDia()){
                climaListEntity.get(i).setClima("Lluvia maxima");
                found = true;
            }
        }

        return climaListEntity;
    }

    @Override
    public Clima calcularClimaPorDia() {
        return null;
    }
}

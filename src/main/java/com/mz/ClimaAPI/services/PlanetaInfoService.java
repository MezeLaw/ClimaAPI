package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.dto.PlanetaInfoDto;

public interface PlanetaInfoService {
    /**
     *  Retorna las posiciones entre otros datos, de una 3-upla de planetas y su estado en un 'n' dia
     * */
    PlanetaInfoDto obtenerPosicionesPlanetas(int dia);

    /**
    * Retorna el resultado del dia respecto a clima, etc, recibiendo los 3 planetas en ese 'n' dia.
    * */

    ClimaResponseDto obtenerClimaGalaxia(PlanetaInfoDto planetaInfoDto);


}

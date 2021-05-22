package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;

import java.util.ArrayList;

public interface ClimaService {

    ClimaResponseDto obtenerClimaPorDia(int dia);
    ArrayList<ClimaResponseDto> obtenerClimaDeDiezAnos();

}

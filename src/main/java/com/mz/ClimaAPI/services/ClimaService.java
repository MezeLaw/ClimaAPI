package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.models.ClimaResponse;

import java.util.ArrayList;

public interface ClimaService {

    ClimaResponseDto obtenerClimaPorDia(int dia);
    ArrayList<ClimaResponse> obtenerClimaDeDiezAnos();

}

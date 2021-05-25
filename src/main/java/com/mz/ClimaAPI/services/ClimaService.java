package com.mz.ClimaAPI.services;

import com.mz.ClimaAPI.dto.ClimaResponseDto;
import com.mz.ClimaAPI.models.Clima;

import java.util.ArrayList;

public interface ClimaService {

    ClimaResponseDto calcularClimaPorDia(int dia);
    ArrayList<Clima> obtenerClimaDeDiezAnos();
    Clima calcularClimaPorDia();
}

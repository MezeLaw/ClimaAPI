package com.mz.ClimaAPI.repositories;

import com.mz.ClimaAPI.models.ClimaResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaRepository extends CrudRepository<ClimaResponse, Long> {
}

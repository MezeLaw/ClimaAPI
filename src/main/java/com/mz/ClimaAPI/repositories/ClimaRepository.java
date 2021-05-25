package com.mz.ClimaAPI.repositories;

import com.mz.ClimaAPI.models.Clima;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaRepository extends CrudRepository<Clima, Long> {

    @Query("SELECT c FROM Clima c where c.dia = :dia")
    Clima findByDia(@Param("dia") int dia);
}

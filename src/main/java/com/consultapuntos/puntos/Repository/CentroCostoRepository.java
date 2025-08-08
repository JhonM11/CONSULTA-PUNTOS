package com.consultapuntos.puntos.Repository;

import com.consultapuntos.puntos.Entity.CentroCosto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentroCostoRepository extends JpaRepository<CentroCosto, Long> {
    Optional<CentroCosto> findByCode(Integer code);
    boolean existsByNameIgnoreCase(String name);
    Optional<CentroCosto> findByNameIgnoreCase(String name);

    // Sugerencia para generar el siguiente code sin escanear todo
    Optional<CentroCosto> findTopByOrderByCodeDesc();

}

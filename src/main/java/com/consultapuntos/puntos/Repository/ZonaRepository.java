package com.consultapuntos.puntos.Repository;

import com.consultapuntos.puntos.Entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    Optional<Zona> findByCode(Integer code);
    boolean existsByNameIgnoreCase(String name);
}


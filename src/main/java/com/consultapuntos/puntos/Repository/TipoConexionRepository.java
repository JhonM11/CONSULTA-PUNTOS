package com.consultapuntos.puntos.Repository;


import com.consultapuntos.puntos.Dto.TypeConexion;
import com.consultapuntos.puntos.Entity.TipoConexion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoConexionRepository extends JpaRepository<TipoConexion, Long> {
    Optional<TipoConexion> findByCode(Integer code);
    boolean existsByCode(Integer code);
    boolean existsByNameIgnoreCase(String name);

}
package com.consultapuntos.puntos.Repository;

import com.consultapuntos.puntos.Entity.Puntos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PuntosRepository extends JpaRepository<Puntos, Integer> {

    List<Puntos> findByCodigo(Integer codigo); // Buscar por código

    @Query("SELECT p FROM Puntos p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Puntos> findByNombreContainingIgnoreCase(@Param("nombre") String nombre); // Buscar por nombre ignorando mayúsculas y minúsculas

    @Query("SELECT p FROM Puntos p WHERE " +
            "p.ipRadio LIKE %:ip% OR " +
            "p.ipTelefono LIKE %:ip% OR " +
            "p.pcAdmin1 LIKE %:ip% OR " +
            "p.pcAdmin2 LIKE %:ip% OR " +
            "p.rBetplay LIKE %:ip% OR " +
            "p.dvr LIKE %:ip% OR " +
            "p.raspberry LIKE %:ip% OR " +
            "p.pcVenta LIKE %:ip% OR " +
            "p.pcAdmin3 LIKE %:ip%")
    List<Puntos> findByIp(@Param("ip") String ip); // Buscar en múltiples campos relacionados con IP


}

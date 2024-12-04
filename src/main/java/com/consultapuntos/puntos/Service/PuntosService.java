package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Entity.Puntos;

import java.util.List;

public interface PuntosService {
    Puntos create(Puntos punto); // Crear un nuevo registro

    Puntos update(Integer codigo, Puntos punto); // Actualizar propiedades por código

    List<Puntos> list(); // Listar todos los puntos

    List<Puntos> findByCodigo(Integer codigo); // Buscar por código

    List<Puntos> findByNombre(String nombre); // Buscar por nombre

    List<Puntos> findByIp(String ip); // Buscar por IP

    void delete(Integer codigo); // Eliminar un punto por código
}

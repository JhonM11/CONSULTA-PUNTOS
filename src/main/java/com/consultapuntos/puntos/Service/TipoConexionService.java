package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.TypeConexion;
import com.consultapuntos.puntos.Entity.TipoConexion;

import java.util.List;

public interface TipoConexionService {
    TypeConexion findByCode(Integer code);
    List<TypeConexion> findAllDto();
    TypeConexion updateNameConexion(Integer code, String newName);
    TypeConexion createConexion(String name);
}

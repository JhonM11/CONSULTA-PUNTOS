package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.TypeConexion;
import com.consultapuntos.puntos.Entity.TipoConexion;
import com.consultapuntos.puntos.Exception.NotFoundException;
import com.consultapuntos.puntos.Exception.DuplicateResourceException;
import com.consultapuntos.puntos.Repository.TipoConexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoConexionServiceImpl implements TipoConexionService {

    @Autowired
    private TipoConexionRepository tipoConexionRepository;

    @Override
    public TypeConexion findByCode(Integer code) {
        TipoConexion entity = tipoConexionRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("TipoConexion no encontrada para el code: " + code));
        return new TypeConexion(entity.getCode(), entity.getName());
    }

    @Override
    public List<TypeConexion> findAllDto() {
        return tipoConexionRepository.findAll().stream()
                .map(conn -> new TypeConexion(conn.getCode(), conn.getName()))
                .toList();
    }

    @Override
    public TypeConexion updateNameConexion(Integer code, String newName) {
        TipoConexion conexion = tipoConexionRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("TipoConexion no encontrada para el code: " + code));
        conexion.setName(newName);
        tipoConexionRepository.save(conexion);
        return new TypeConexion(conexion.getCode(), conexion.getName());
    }

    @Override
    public TypeConexion createConexion(String name) {
        if (tipoConexionRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Ya existe una conexión con ese nombre");
        }
        TipoConexion nueva = new TipoConexion();
        nueva.setCode(generateNextCode());
        nueva.setName(name);
        tipoConexionRepository.save(nueva);
        return new TypeConexion(nueva.getCode(), nueva.getName());
    }


    private Integer generateNextCode() {
        return tipoConexionRepository.findAll().stream()
                .map(TipoConexion::getCode)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
}
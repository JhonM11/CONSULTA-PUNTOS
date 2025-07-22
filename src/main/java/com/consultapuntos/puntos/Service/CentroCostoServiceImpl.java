package com.consultapuntos.puntos.Service;


import com.consultapuntos.puntos.Dto.CentroCostoDto;
import com.consultapuntos.puntos.Entity.CentroCosto;
import com.consultapuntos.puntos.Exception.DuplicateResourceException;
import com.consultapuntos.puntos.Exception.NotFoundException;
import com.consultapuntos.puntos.Repository.CentroCostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroCostoServiceImpl implements CentroCostoService {

    @Autowired
    private CentroCostoRepository centroCostoRepository;

    @Override
    public CentroCostoDto findByCode(Integer code) {
        CentroCosto cc = centroCostoRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("CentroCosto no encontrado para el code: " + code));
        return new CentroCostoDto(cc.getCode(), cc.getName());
    }

    @Override
    public List<CentroCostoDto> findAll() {
        return centroCostoRepository.findAll().stream()
                .map(z -> new CentroCostoDto(z.getCode(), z.getName()))
                .toList();
    }

    @Override
    public CentroCostoDto updateName(Integer code, String newName) {
        CentroCosto cc = centroCostoRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("CentroCosto no encontrado para el code: " + code));
        cc.setName(newName);
        centroCostoRepository.save(cc);
        return new CentroCostoDto(cc.getCode(), cc.getName());
    }

    @Override
    public CentroCostoDto createCentro(String name) {
        if (centroCostoRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Ya existe un centro de costo con ese nombre");
        }
        CentroCosto nuevo = new CentroCosto();
        nuevo.setCode(generateNextCode());
        nuevo.setName(name);
        centroCostoRepository.save(nuevo);
        return new CentroCostoDto(nuevo.getCode(), nuevo.getName());
    }

    private Integer generateNextCode() {
        return centroCostoRepository.findAll().stream()
                .map(CentroCosto::getCode)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
}
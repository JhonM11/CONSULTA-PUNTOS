package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CentroCostoDto;
import com.consultapuntos.puntos.Entity.CentroCosto;
import com.consultapuntos.puntos.Entity.Zona;
import com.consultapuntos.puntos.Exception.DuplicateResourceException;
import com.consultapuntos.puntos.Exception.NotFoundException;
import com.consultapuntos.puntos.Repository.CentroCostoRepository;
import com.consultapuntos.puntos.Repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroCostoServiceImpl implements CentroCostoService {

    @Autowired
    private CentroCostoRepository centroCostoRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public CentroCostoDto findByCode(Integer code) {
        CentroCosto cc = centroCostoRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("CentroCosto no encontrado para el code: " + code));
        return toDto(cc);
    }

    @Override
    public List<CentroCostoDto> findAll() {
        return centroCostoRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CentroCostoDto updateName(Integer code, String newName) {
        CentroCosto cc = centroCostoRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("CentroCosto no encontrado para el code: " + code));
        cc.setName(newName);
        centroCostoRepository.save(cc);
        return toDto(cc);
    }

    @Override
    public CentroCostoDto createCentro(String name, Integer zonaCode) {
        if (centroCostoRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Ya existe un centro de costo con ese nombre");
        }

        Zona zona = zonaRepository.findByCode(zonaCode)
                .orElseThrow(() -> new NotFoundException("Zona no encontrada para el code: " + zonaCode));

        CentroCosto nuevo = new CentroCosto();
        nuevo.setCode(generateNextCode());
        nuevo.setName(name);
        nuevo.setZona(zona); // asigna la zona (FK por code)

        centroCostoRepository.save(nuevo);
        return toDto(nuevo);
    }

    private Integer generateNextCode() {
        return centroCostoRepository.findTopByOrderByCodeDesc()
                .map(CentroCosto::getCode)
                .map(max -> max + 1)
                .orElse(1);
    }

    private CentroCostoDto toDto(CentroCosto cc) {
        return new CentroCostoDto(
                cc.getCode(),
                cc.getName(),
                cc.getZona() != null ? cc.getZona().getCode() : null,
                cc.getZona() != null ? cc.getZona().getName() : null
        );
    }
}

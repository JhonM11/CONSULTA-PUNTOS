package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.ZonaDto;
import com.consultapuntos.puntos.Entity.Zona;
import com.consultapuntos.puntos.Exception.DuplicateResourceException;
import com.consultapuntos.puntos.Exception.NotFoundException;
import com.consultapuntos.puntos.Repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaServiceImpl implements ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public ZonaDto findByCode(Integer code) {
        Zona zona = zonaRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Zona no encontrada para el code: " + code));
        return new ZonaDto(zona.getCode(), zona.getName());
    }

    @Override
    public List<ZonaDto> findAll() {
        return zonaRepository.findAll().stream()
                .map(z -> new ZonaDto(z.getCode(), z.getName()))
                .toList();
    }

    @Override
    public ZonaDto updateName(Integer code, String newName) {
        Zona zona = zonaRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Zona no encontrada para el code: " + code));
        zona.setName(newName);
        zonaRepository.save(zona);
        return new ZonaDto(zona.getCode(), zona.getName());
    }

    @Override
    public ZonaDto createZona(String name) {
        if (zonaRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Ya existe una zona con ese nombre");
        }
        Zona nueva = new Zona();
        nueva.setCode(generateNextCode());
        nueva.setName(name);
        zonaRepository.save(nueva);
        return new ZonaDto(nueva.getCode(), nueva.getName());
    }

    private Integer generateNextCode() {
        return zonaRepository.findAll().stream()
                .map(Zona::getCode)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
}
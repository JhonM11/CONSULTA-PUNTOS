package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.ZonaDto;

import java.util.List;

public interface ZonaService {
    ZonaDto findByCode(Integer code);
    List<ZonaDto> findAll();
    ZonaDto updateName(Integer code, String newName);
    ZonaDto createZona(String name);
}


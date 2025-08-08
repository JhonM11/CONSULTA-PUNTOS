package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CentroCostoDto;

import java.util.List;

public interface CentroCostoService {
    CentroCostoDto findByCode(Integer code);
    List<CentroCostoDto> findAll();
    CentroCostoDto updateName(Integer code, String newName);
    CentroCostoDto createCentro(String name, Integer zonaCode);
}
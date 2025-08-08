package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CentroCostoDto;
import com.consultapuntos.puntos.Service.CentroCostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;

@RestController
public class CentroCostoController {

    @Autowired
    private CentroCostoService centroCostoService;

    @GetMapping(CCOSTO_FIND_BY_CODE)
    public ResponseEntity<CentroCostoDto> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(centroCostoService.findByCode(code));
    }

    @GetMapping(CCOSTO_LIST)
    public ResponseEntity<List<CentroCostoDto>> findAll() {
        return ResponseEntity.ok(centroCostoService.findAll());
    }

    @PatchMapping(CCOSTO_UPDATE_NAME)
    public ResponseEntity<CentroCostoDto> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(centroCostoService.updateName(code, newName));
    }

    @PostMapping(CCOSTO_CREATE)
    public ResponseEntity<CentroCostoDto> create(@RequestParam String name,
                                                 @RequestParam Integer zonaCode) {
        return ResponseEntity.ok(centroCostoService.createCentro(name, zonaCode));
    }
}

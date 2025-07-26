package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.ZonaDto;
import com.consultapuntos.puntos.Service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;


@RestController
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    @GetMapping(ZONAS_FIND_BY_CODE)
    public ResponseEntity<ZonaDto> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(zonaService.findByCode(code));
    }

    @GetMapping(ZONAS_GET_ALL)
    public ResponseEntity<List<ZonaDto>> findAll() {
        return ResponseEntity.ok(zonaService.findAll());
    }

    @PatchMapping(ZONAS_UPDATE_NAME)
    public ResponseEntity<ZonaDto> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(zonaService.updateName(code, newName));
    }

    @PostMapping(ZONAS_CREATE)
    public ResponseEntity<ZonaDto> create(@RequestParam String name) {
        return ResponseEntity.ok(zonaService.createZona(name));
    }
}

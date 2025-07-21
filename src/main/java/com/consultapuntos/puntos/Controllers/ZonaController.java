package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.ZonaDto;
import com.consultapuntos.puntos.Service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/puntos/zonas")
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    @GetMapping("/findByCode/{code}")
    public ResponseEntity<ZonaDto> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(zonaService.findByCode(code));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ZonaDto>> findAll() {
        return ResponseEntity.ok(zonaService.findAll());
    }

    @PatchMapping("/updateName/{code}")
    public ResponseEntity<ZonaDto> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(zonaService.updateName(code, newName));
    }

    @PostMapping("/create")
    public ResponseEntity<ZonaDto> create(@RequestParam String name) {
        return ResponseEntity.ok(zonaService.createZona(name));
    }
}

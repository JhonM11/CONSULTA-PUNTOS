package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CentroCostoDto;
import com.consultapuntos.puntos.Service.CentroCostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/puntos/centros-costos")
public class CentroCostoController {

    @Autowired
    private CentroCostoService centroCostoService;

    @GetMapping("/findByCode/{code}")
    public ResponseEntity<CentroCostoDto> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(centroCostoService.findByCode(code));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CentroCostoDto>> findAll() {
        return ResponseEntity.ok(centroCostoService.findAll());
    }

    @PatchMapping("/updateName/{code}")
    public ResponseEntity<CentroCostoDto> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(centroCostoService.updateName(code, newName));
    }

    @PostMapping("/create")
    public ResponseEntity<CentroCostoDto> create(@RequestParam String name) {
        return ResponseEntity.ok(centroCostoService.createCentro(name));
    }
}

package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreateTypeConexion;
import com.consultapuntos.puntos.Dto.TypeConexion;
import com.consultapuntos.puntos.Entity.TipoConexion;
import com.consultapuntos.puntos.Service.TipoConexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/puntos/tipo-conexiones")
public class TipoConexionController {

    @Autowired
    private TipoConexionService tipoConexionService;

    @GetMapping("/findTypeConnectionBycode/{code}")
    public ResponseEntity<TypeConexion> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(tipoConexionService.findByCode(code));
    }

    @GetMapping("/getAllConnection")
    public ResponseEntity<List<TypeConexion>> findAll() {
        return ResponseEntity.ok(tipoConexionService.findAllDto());
    }

    @PatchMapping("/updateTypeConnectionByCode/{code}")
    public ResponseEntity<TypeConexion> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(tipoConexionService.updateNameConexion(code, newName));
    }

    @PostMapping("/createTypeConnection")
    public ResponseEntity<TypeConexion> createConexion(@RequestBody CreateTypeConexion request) {
        return ResponseEntity.ok(tipoConexionService.createConexion(request.getName()));
    }
}
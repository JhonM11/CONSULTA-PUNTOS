package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreateTypeConexion;
import com.consultapuntos.puntos.Dto.TypeConexion;
import com.consultapuntos.puntos.Entity.TipoConexion;
import com.consultapuntos.puntos.Service.TipoConexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;

@RestController
public class TipoConexionController {

    @Autowired
    private TipoConexionService tipoConexionService;

    @GetMapping(TIPO_CONEXION_FIND_BY_CODE)
    public ResponseEntity<TypeConexion> findByCode(@PathVariable Integer code) {
        return ResponseEntity.ok(tipoConexionService.findByCode(code));
    }

    @GetMapping(TIPO_CONEXION_GET_ALL)
    public ResponseEntity<List<TypeConexion>> findAll() {
        return ResponseEntity.ok(tipoConexionService.findAllDto());
    }

    @PatchMapping(TIPO_CONEXION_UPDATE)
    public ResponseEntity<TypeConexion> updateName(@PathVariable Integer code, @RequestParam String newName) {
        return ResponseEntity.ok(tipoConexionService.updateNameConexion(code, newName));
    }

    @PostMapping(TIPO_CONEXION_CREATE)
    public ResponseEntity<TypeConexion> createConexion(@RequestBody CreateTypeConexion request) {
        return ResponseEntity.ok(tipoConexionService.createConexion(request.getName()));
    }
}
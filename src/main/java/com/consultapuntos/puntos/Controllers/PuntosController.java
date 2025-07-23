package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreatePointRequest;
import com.consultapuntos.puntos.Dto.PointResponse;
import com.consultapuntos.puntos.Dto.UpdatePointRequest;
import com.consultapuntos.puntos.Entity.Puntos;
import com.consultapuntos.puntos.Service.PuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/puntos")
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @PostMapping("/create")
    public ResponseEntity<PointResponse> create(@RequestBody CreatePointRequest request) {
        PointResponse creado = puntosService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    // Manejo de excepciones global dentro del controlador
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @PutMapping("/update/{codigo}")
    public ResponseEntity<PointResponse> update(@PathVariable Integer codigo, @RequestBody UpdatePointRequest request) {
        PointResponse actualizado = puntosService.update(codigo, request);
        return ResponseEntity.ok(actualizado);
    }


    @GetMapping("/list")
    public ResponseEntity<List<PointResponse>> list() {
        return ResponseEntity.ok(puntosService.list());
    }

    @GetMapping("/findByCodigo/{codigo}")
    public ResponseEntity<List<PointResponse>> findByCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(puntosService.findByCodigo(codigo));
    }

    @GetMapping("/findByNombre/{nombre}")
    public ResponseEntity<List<PointResponse>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(puntosService.findByNombre(nombre));
    }

    @GetMapping("/findByIp/{ip}")
    public ResponseEntity<List<PointResponse>> findByIp(@PathVariable String ip) {
        return ResponseEntity.ok(puntosService.findByIp(ip));
    }

    @GetMapping("/findByCodigoAsText/{texto}")
    public ResponseEntity<List<PointResponse>> findByCodigoAsText(@PathVariable String texto) {
        return ResponseEntity.ok(puntosService.findByCodigoAsText(texto));
    }


    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
        puntosService.delete(codigo);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido
    }



}

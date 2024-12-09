package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Entity.Puntos;
import com.consultapuntos.puntos.Service.PuntosService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Puntos> create(@RequestBody Puntos punto) {
        return ResponseEntity.ok(puntosService.create(punto));
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<Puntos> update(@PathVariable Integer codigo, @RequestBody Puntos punto) {
        return ResponseEntity.ok(puntosService.update(codigo, punto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Puntos>> list() {
        return ResponseEntity.ok(puntosService.list());
    }

    @GetMapping("/findByCodigo/{codigo}")
    public ResponseEntity<List<Puntos>> findByCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(puntosService.findByCodigo(codigo));
    }

    @GetMapping("/findByNombre/{nombre}")
    public ResponseEntity<List<Puntos>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(puntosService.findByNombre(nombre));
    }

    @GetMapping("/findByIp/{ip}")
    public ResponseEntity<List<Puntos>> findByIp(@PathVariable String ip) {
        return ResponseEntity.ok(puntosService.findByIp(ip));
    }

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
        puntosService.delete(codigo);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido
    }

    @GetMapping("/findByCodigoAsText/{codigoTexto}")
    public ResponseEntity<List<Puntos>> findByCodigoAsText(@PathVariable String codigoTexto) {
        return ResponseEntity.ok(puntosService.findByCodigoAsText(codigoTexto));
    }

}

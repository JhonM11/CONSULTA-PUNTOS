package com.consultapuntos.puntos.Controllers;

import com.consultapuntos.puntos.Dto.CreatePointRequest;
import com.consultapuntos.puntos.Dto.PointResponse;
import com.consultapuntos.puntos.Dto.UpdatePointRequest;
import com.consultapuntos.puntos.Entity.Puntos;
import com.consultapuntos.puntos.Service.PuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.consultapuntos.puntos.Security.Config.ApiRoutes.*;

@RestController
public class PuntosController {

    @Autowired
    private PuntosService puntosService;

    @PostMapping(PUNTOS_CREATE)
    public ResponseEntity<PointResponse> create(@RequestBody CreatePointRequest request) {
        PointResponse creado = puntosService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    // Manejo de excepciones global dentro del controlador
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @PutMapping(PUNTOS_UPDATE)
    public ResponseEntity<PointResponse> update(@PathVariable Integer codigo, @RequestBody UpdatePointRequest request) {
        PointResponse actualizado = puntosService.update(codigo, request);
        return ResponseEntity.ok(actualizado);
    }


    @GetMapping(PUNTOS_LIST)
    public ResponseEntity<List<PointResponse>> list() {
        return ResponseEntity.ok(puntosService.list());
    }

    @GetMapping(PUNTOS_FIND_BY_CODIGO)
    public ResponseEntity<List<PointResponse>> findByCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(puntosService.findByCodigo(codigo));
    }

    @GetMapping(PUNTOS_FIND_BY_NOMBRE)
    public ResponseEntity<List<PointResponse>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(puntosService.findByNombre(nombre));
    }

    @GetMapping(PUNTOS_FIND_BY_IP)
    public ResponseEntity<List<PointResponse>> findByIp(@PathVariable String ip) {
        return ResponseEntity.ok(puntosService.findByIp(ip));
    }

    @GetMapping(PUNTOS_FIND_BY_CODIGO_AS_TEXT)
    public ResponseEntity<List<PointResponse>> findByCodigoAsText(@PathVariable String texto) {
        return ResponseEntity.ok(puntosService.findByCodigoAsText(texto));
    }


    @DeleteMapping(PUNTOS_DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
        puntosService.delete(codigo);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido
    }


    @GetMapping(PUNTOS_REPORTS)
    public ResponseEntity<byte[]> descargarReporte(
            @RequestParam(required = false) List<Integer> tipoConexionCode,
            @RequestParam(required = false) List<Integer> zonaCode,
            @RequestParam(required = false) List<Integer> centroCostoCode) {

        byte[] archivo = puntosService.generateReport(tipoConexionCode, zonaCode, centroCostoCode);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporte_puntos.xlsx")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(archivo);
    }

    @GetMapping(PUNTOS_REPORTS_FORMAT_ANSIBLE)
    public ResponseEntity<byte[]> descargarReporteWirelessTxt(
            @RequestParam(required = false) List<Integer> centroCostoCode,
            @RequestParam(required = false) List<Integer> zonaCode,
            @RequestParam(required = false) List<Integer> tipoConexionCode,
            @RequestParam(defaultValue = "puntos_formato_ansible") String filename) {

        byte[] archivo = puntosService.generatePlainTextReportForWireless(centroCostoCode, zonaCode, tipoConexionCode);

        String encodedFilename = URLEncoder.encode(filename.trim(), StandardCharsets.UTF_8)
                .replace("+", " ");

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"")
                .header("Content-Type", "text/plain")
                .body(archivo);
    }






    @PostMapping(PUNTOS_IMPORT_EXCEL)
    public ResponseEntity<List<PointResponse>> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<PointResponse> insertedPoints = puntosService.bulkInsertFromExcel(file);
        return ResponseEntity.ok(insertedPoints);
    }



    @GetMapping(PUNTOS_DOWNLOAD_TEMPLATE)
    public ResponseEntity<Resource> descargarPlantillaFisica() throws IOException {
        ClassPathResource plantilla = new ClassPathResource("static/templates/plantillaImport.xlsx");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=plantillaImport.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(plantilla.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(plantilla.getInputStream()));
    }



}

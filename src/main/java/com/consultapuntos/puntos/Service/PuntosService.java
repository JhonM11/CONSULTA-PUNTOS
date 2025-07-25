package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CreatePointRequest;
import com.consultapuntos.puntos.Dto.PointResponse;
import com.consultapuntos.puntos.Dto.UpdatePointRequest;
import com.consultapuntos.puntos.Entity.Puntos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PuntosService {

    PointResponse create(CreatePointRequest request); // Crear un nuevo registro

    PointResponse update(Integer codigo, UpdatePointRequest request);  // Actualizar propiedades por código

    List<PointResponse> list();
    List<PointResponse> findByCodigo(Integer codigo);
    List<PointResponse> findByNombre(String nombre);
    List<PointResponse> findByIp(String ip);
    List<PointResponse> findByCodigoAsText(String codigoTexto);

    void delete(Integer codigo); // Eliminar un punto por código
    boolean existsByCodigo(Integer codigo);

    byte[] generateReport(Integer tipoConexionCode, Integer zonaCode, Integer centroCostoCode);

    byte[] generatePlainTextReportForWireless(Integer centroCostoCode, Integer zonaCode);



    List<PointResponse> bulkInsertFromExcel(MultipartFile file);

    Integer getTipoConexionCodeByName(String name);
    Integer getZonaCodeByName(String name);
    Integer getCentroCostoCodeByName(String name);



}

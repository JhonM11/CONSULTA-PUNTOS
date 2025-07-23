package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.CreatePointRequest;
import com.consultapuntos.puntos.Dto.PointResponse;
import com.consultapuntos.puntos.Dto.UpdatePointRequest;
import com.consultapuntos.puntos.Entity.Puntos;

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

}

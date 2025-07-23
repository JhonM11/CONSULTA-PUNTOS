package com.consultapuntos.puntos.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PointResponse {
    private Integer codigo;
    private String nombre;
    private String tecnologia;
    private String observacion;
    private String ipRadio;
    private String ipTelefono;
    private String raspberry;
    private String rbetplay;
    private String dvr;
    private String pcVenta;
    private String pcAdmin1;
    private String pcAdmin2;
    private String pcAdmin3;
    private String nota;
    private RelatedData tipoConexion;
    private RelatedData zona;
    private RelatedData centroCosto;

    @Getter
    @Setter
    @Builder
    public static class RelatedData {
        private Integer code;
        private String name;
    }
}
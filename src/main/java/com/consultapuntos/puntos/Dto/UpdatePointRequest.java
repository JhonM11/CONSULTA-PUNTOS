package com.consultapuntos.puntos.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePointRequest {
    private String nombre;
    private Integer centroCostoCode;
    private String tecnologia;
    private String observacion;
    private String ipRadio;
    private String ipTelefono;
    private String raspberry;
    private String rbetplay; // Asegúrate de que este nombre coincida con el getter y setter
    private String dvr;
    private String pcVenta;
    private String pcAdmin1;
    private String pcAdmin2;
    private String pcAdmin3;
    private String nota;
    private Integer tipoConexionCode;
    private Integer zonaCode;
}

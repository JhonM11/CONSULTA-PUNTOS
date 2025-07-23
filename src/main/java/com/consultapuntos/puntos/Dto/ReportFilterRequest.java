package com.consultapuntos.puntos.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportFilterRequest {
    private Integer tipoConexionCode;
    private Integer zonaCode;
    private Integer centroCostoCode;
}

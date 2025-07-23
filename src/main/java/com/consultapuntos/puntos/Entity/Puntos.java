package com.consultapuntos.puntos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "puntos")
@Getter
@Setter
@NoArgsConstructor
public class Puntos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tecnologia", nullable = false, length = 20)
    private String tecnologia;

    @Column(name = "observacion", nullable = false, length = 150)
    private String observacion;

    @Column(name = "ipradio", nullable = false, length = 45)
    private String ipRadio;

    @Column(name = "iptelefono", nullable = false, length = 20)
    private String ipTelefono;

    @Column(name = "Raspberry", nullable = false, length = 25)
    private String raspberry;

    @Column(name = "Rbetplay", nullable = false, length = 25)
    private String rbetplay;

    @Column(name = "Dvr", nullable = false, length = 25)
    private String dvr;

    @Column(name = "Pc_Venta", nullable = false, length = 25)
    private String pcVenta;

    @Column(name = "Pc_Admin1", nullable = false, length = 25)
    private String pcAdmin1;

    @Column(name = "Pc_Admin2", nullable = false, length = 25)
    private String pcAdmin2;

    @Column(name = "Pc_Admin3", nullable = false, length = 25)
    private String pcAdmin3;

    @Column(name = "nota", columnDefinition = "MEDIUMTEXT", nullable = false)
    private String nota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codetypeconnection", referencedColumnName = "code")
    private TipoConexion tipoConexion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codezona", referencedColumnName = "code")
    private Zona zona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centrocosto", referencedColumnName = "code")
    private CentroCosto centroCosto;
}

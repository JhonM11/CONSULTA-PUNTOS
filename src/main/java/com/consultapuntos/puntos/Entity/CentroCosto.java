package com.consultapuntos.puntos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "centros_costos")
@Getter
@Setter
public class CentroCosto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "zona_code",               // FK en centros_costos
            referencedColumnName = "code",    // Columna en la tabla zonas
            nullable = false
    )
    private Zona zona;

}


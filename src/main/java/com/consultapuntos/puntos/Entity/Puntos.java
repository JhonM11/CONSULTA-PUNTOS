package com.consultapuntos.puntos.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "puntos")
public class Puntos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id; // Mapea la columna 'id'

        @Column(name = "codigo",nullable = false, unique = true)
        private Integer codigo; // Mapea la columna 'codigo'

        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre; // Mapea la columna 'nombre'

        @Column(name = "centrocosto", nullable = false, length = 100)
        private String centroCosto; // Mapea la columna 'centrocosto'

        @Column(name = "tecnologia",nullable = false, length = 20)
        private String tecnologia; // Mapea la columna 'tecnologia'

        @Column(name = "observacion",nullable = false, length = 150)
        private String observacion; // Mapea la columna 'observacion'

        @Column(name = "ipradio",nullable = false, length = 45)
        private String ipRadio; // Mapea la columna 'ipradio'

        @Column(name = "iptelefono",nullable = false, length = 20)
        private String ipTelefono; // Mapea la columna 'iptelefono'

        @Column(name = "Raspberry", nullable = false, length = 25)
        private String raspberry; // Mapea la columna 'Raspberry'

        @Column(name = "Rbetplay", nullable = false, length = 25)
        private String rBetplay; // Mapea la columna 'Rbetplay'

        @Column(name = "Dvr", nullable = false, length = 25)
        private String dvr; // Mapea la columna 'Dvr'

        @Column(name = "Pc_Venta", nullable = false, length = 25)
        private String pcVenta; // Mapea la columna 'Pc_Venta'

        @Column(name = "Pc_Admin1", nullable = false, length = 25)
        private String pcAdmin1; // Mapea la columna 'Pc_Admin1'

        @Column(name = "Pc_Admin2", nullable = false, length = 25)
        private String pcAdmin2; // Mapea la columna 'Pc_Admin2'

        @Column(name = "Pc_Admin3", nullable = false, length = 25)
        private String pcAdmin3; // Mapea la columna 'Pc_Admin3'

        @Column(name = "nota",columnDefinition = "MEDIUMTEXT", nullable = false)
        private String nota; // Mapea la columna 'nota'


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIpRadio() {
        return ipRadio;
    }

    public void setIpRadio(String ipRadio) {
        this.ipRadio = ipRadio;
    }

    public String getIpTelefono() {
        return ipTelefono;
    }

    public void setIpTelefono(String ipTelefono) {
        this.ipTelefono = ipTelefono;
    }

    public String getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(String raspberry) {
        this.raspberry = raspberry;
    }

    public String getrBetplay() {
        return rBetplay;
    }

    public void setrBetplay(String rBetplay) {
        this.rBetplay = rBetplay;
    }

    public String getDvr() {
        return dvr;
    }

    public void setDvr(String dvr) {
        this.dvr = dvr;
    }

    public String getPcVenta() {
        return pcVenta;
    }

    public void setPcVenta(String pcVenta) {
        this.pcVenta = pcVenta;
    }

    public String getPcAdmin1() {
        return pcAdmin1;
    }

    public void setPcAdmin1(String pcAdmin1) {
        this.pcAdmin1 = pcAdmin1;
    }

    public String getPcAdmin2() {
        return pcAdmin2;
    }

    public void setPcAdmin2(String pcAdmin2) {
        this.pcAdmin2 = pcAdmin2;
    }

    public String getPcAdmin3() {
        return pcAdmin3;
    }

    public void setPcAdmin3(String pcAdmin3) {
        this.pcAdmin3 = pcAdmin3;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}


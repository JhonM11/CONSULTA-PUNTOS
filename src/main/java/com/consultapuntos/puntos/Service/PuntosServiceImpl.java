package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Entity.Puntos;
import com.consultapuntos.puntos.Repository.PuntosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntosServiceImpl implements PuntosService {

    @Autowired
    private PuntosRepository puntosRepository;


    public Puntos create(Puntos punto) {
        return puntosRepository.save(punto); // Crear un nuevo registro
    }


    public Puntos update(Integer codigo, Puntos punto) {
        Optional<Puntos> existingPunto = puntosRepository.findByCodigo(codigo).stream().findFirst();
        if (existingPunto.isPresent()) {
            Puntos updatedPunto = existingPunto.get();
            updatedPunto.setNombre(punto.getNombre());
            updatedPunto.setCentroCosto(punto.getCentroCosto());
            updatedPunto.setTecnologia(punto.getTecnologia());
            updatedPunto.setObservacion(punto.getObservacion());
            updatedPunto.setIpRadio(punto.getIpRadio());
            updatedPunto.setIpTelefono(punto.getIpTelefono());
            updatedPunto.setRaspberry(punto.getRaspberry());
            updatedPunto.setrBetplay(punto.getrBetplay());
            updatedPunto.setDvr(punto.getDvr());
            updatedPunto.setPcVenta(punto.getPcVenta());
            updatedPunto.setPcAdmin1(punto.getPcAdmin1());
            updatedPunto.setPcAdmin2(punto.getPcAdmin2());
            updatedPunto.setPcAdmin3(punto.getPcAdmin3());
            updatedPunto.setNota(punto.getNota());
            return puntosRepository.save(updatedPunto);
        }
        throw new RuntimeException("Punto no encontrado con el código: " + codigo);
    }


    public List<Puntos> list() {
        return puntosRepository.findAll(); // Listar todos los registros
    }


    public List<Puntos> findByCodigo(Integer codigo) {
        return puntosRepository.findByCodigo(codigo); // Buscar por código
    }


    public List<Puntos> findByNombre(String nombre) {
        return puntosRepository.findByNombreContainingIgnoreCase(nombre); // Buscar por nombre (like)
    }


    public List<Puntos> findByIp(String ip) {
        return puntosRepository.findByIp(ip); // Buscar en campos relacionados con IP
    }
}

package com.consultapuntos.puntos.Service;

import com.consultapuntos.puntos.Dto.*;
import com.consultapuntos.puntos.Entity.CentroCosto;
import com.consultapuntos.puntos.Entity.Puntos;
import com.consultapuntos.puntos.Entity.TipoConexion;
import com.consultapuntos.puntos.Entity.Zona;
import com.consultapuntos.puntos.Exception.NotFoundException;
import com.consultapuntos.puntos.Repository.CentroCostoRepository;
import com.consultapuntos.puntos.Repository.PuntosRepository;
import com.consultapuntos.puntos.Repository.TipoConexionRepository;
import com.consultapuntos.puntos.Repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PuntosServiceImpl implements PuntosService {

    @Autowired
    private PuntosRepository puntosRepository;

    @Autowired
    private CentroCostoRepository centroCostoRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private TipoConexionRepository tipoConexionRepository;





    @Override
    public PointResponse create(CreatePointRequest request) {
        if (existsByCodigo(request.getCodigo())) {
            throw new DataIntegrityViolationException("Ya existe un punto con el código: " + request.getCodigo());
        }

        CentroCosto centro = centroCostoRepository.findByCode(request.getCentroCostoCode())
                .orElseThrow(() -> new NotFoundException("Centro de costo no encontrado"));

        Zona zona = zonaRepository.findByCode(request.getZonaCode())
                .orElseThrow(() -> new NotFoundException("Zona no encontrada"));

        TipoConexion tipoConexion = tipoConexionRepository.findByCode(request.getTipoConexionCode())
                .orElseThrow(() -> new NotFoundException("Tipo de conexión no encontrado"));

        Puntos punto = new Puntos();
        punto.setCodigo(request.getCodigo());
        punto.setNombre(request.getNombre());
        punto.setCentroCosto(centro);
        punto.setZona(zona);
        punto.setTipoConexion(tipoConexion);
        punto.setTecnologia(defaultIfNull(request.getTecnologia()));
        punto.setObservacion(defaultIfNull(request.getObservacion()));
        punto.setIpRadio(defaultIfNull(request.getIpRadio()));
        punto.setIpTelefono(defaultIfNull(request.getIpTelefono()));
        punto.setRaspberry(defaultIfNull(request.getRaspberry()));
        punto.setRbetplay(defaultIfNull(request.getRbetplay()));
        punto.setDvr(defaultIfNull(request.getDvr()));
        punto.setPcVenta(defaultIfNull(request.getPcVenta()));
        punto.setPcAdmin1(defaultIfNull(request.getPcAdmin1()));
        punto.setPcAdmin2(defaultIfNull(request.getPcAdmin2()));
        punto.setPcAdmin3(defaultIfNull(request.getPcAdmin3()));
        punto.setNota(defaultIfNull(request.getNota()));

        Puntos creado = puntosRepository.save(punto);

        return mapToDto(creado);
    }



    private String defaultIfNull(String value) {
        return value != null ? value : "";
    }



    private PointResponse mapToDto(Puntos punto) {
        return PointResponse.builder()
                .codigo(punto.getCodigo())
                .nombre(punto.getNombre())
                .tecnologia(punto.getTecnologia())
                .observacion(punto.getObservacion())
                .ipRadio(punto.getIpRadio())
                .ipTelefono(punto.getIpTelefono())
                .raspberry(punto.getRaspberry())
                .rbetplay(punto.getRbetplay())
                .dvr(punto.getDvr())
                .pcVenta(punto.getPcVenta())
                .pcAdmin1(punto.getPcAdmin1())
                .pcAdmin2(punto.getPcAdmin2())
                .pcAdmin3(punto.getPcAdmin3())
                .nota(punto.getNota())
                .tipoConexion(PointResponse.RelatedData.builder()
                        .code(punto.getTipoConexion().getCode())
                        .name(punto.getTipoConexion().getName())
                        .build())
                .zona(PointResponse.RelatedData.builder()
                        .code(punto.getZona().getCode())
                        .name(punto.getZona().getName())
                        .build())
                .centroCosto(PointResponse.RelatedData.builder()
                        .code(punto.getCentroCosto().getCode())
                        .name(punto.getCentroCosto().getName())
                        .build())
                .build();
    }





    @Override
    public boolean existsByCodigo(Integer codigo) {
        return !puntosRepository.findByCodigo(codigo).isEmpty();
    }





    @Override
    public PointResponse update(Integer codigo, UpdatePointRequest request) {
        Optional<Puntos> existingPunto = puntosRepository.findByCodigo(codigo).stream().findFirst();
        if (existingPunto.isEmpty()) {
            throw new NotFoundException("Punto no encontrado con el código: " + codigo);
        }

        CentroCosto centro = centroCostoRepository.findByCode(request.getCentroCostoCode())
                .orElseThrow(() -> new NotFoundException("Centro de costo no encontrado"));

        Zona zona = zonaRepository.findByCode(request.getZonaCode())
                .orElseThrow(() -> new NotFoundException("Zona no encontrada"));

        TipoConexion tipoConexion = tipoConexionRepository.findByCode(request.getTipoConexionCode())
                .orElseThrow(() -> new NotFoundException("Tipo de conexión no encontrado"));

        Puntos punto = existingPunto.get();
        punto.setNombre(request.getNombre());
        punto.setCentroCosto(centro);
        punto.setZona(zona);
        punto.setTipoConexion(tipoConexion);
        punto.setTecnologia(defaultIfNull(request.getTecnologia()));
        punto.setObservacion(defaultIfNull(request.getObservacion()));
        punto.setIpRadio(defaultIfNull(request.getIpRadio()));
        punto.setIpTelefono(defaultIfNull(request.getIpTelefono()));
        punto.setRaspberry(defaultIfNull(request.getRaspberry()));
        punto.setRbetplay(defaultIfNull(request.getRbetplay()));
        punto.setDvr(defaultIfNull(request.getDvr()));
        punto.setPcVenta(defaultIfNull(request.getPcVenta()));
        punto.setPcAdmin1(defaultIfNull(request.getPcAdmin1()));
        punto.setPcAdmin2(defaultIfNull(request.getPcAdmin2()));
        punto.setPcAdmin3(defaultIfNull(request.getPcAdmin3()));
        punto.setNota(defaultIfNull(request.getNota()));

        return mapToDto(puntosRepository.save(punto));
    }




    @Override
    public List<PointResponse> list() {
        return puntosRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }





    @Override
    public List<PointResponse> findByCodigo(Integer codigo) {
        return puntosRepository.findByCodigo(codigo).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }





    @Override
    public List<PointResponse> findByNombre(String nombre) {
        return puntosRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }





    @Override
    public List<PointResponse> findByIp(String ip) {
        return puntosRepository.findByIp(ip).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }





    @Override
    public void delete(Integer codigo) {
        Optional<Puntos> existingPunto = puntosRepository.findByCodigo(codigo).stream().findFirst();
        if (existingPunto.isPresent()) {
            puntosRepository.delete(existingPunto.get());
        } else {
            throw new NotFoundException("Punto no encontrado con el código: " + codigo);
        }
    }




    @Override
    public List<PointResponse> findByCodigoAsText(String codigoTexto) {
        return puntosRepository.findByCodigoAsText(codigoTexto).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }









    @Override
    public byte[] generateReport(List<Integer> tipoConexionCodes, List<Integer> zonaCodes, List<Integer> centroCostoCodes) {
        List<Puntos> puntos = puntosRepository.findAll().stream()
                .filter(p -> tipoConexionCodes == null || tipoConexionCodes.isEmpty() || tipoConexionCodes.contains(p.getTipoConexion().getCode()))
                .filter(p -> zonaCodes == null || zonaCodes.isEmpty() || zonaCodes.contains(p.getZona().getCode()))
                .filter(p -> centroCostoCodes == null || centroCostoCodes.isEmpty() || centroCostoCodes.contains(p.getCentroCosto().getCode()))
                .collect(Collectors.toList());

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte Puntos");

            Row header = sheet.createRow(0);
            String[] columns = {"Código", "Nombre", "Tecnología", "Observación", "IP Radio", "IP Teléfono",
                    "Raspberry", "Rbetplay", "DVR", "PC Venta", "PC Admin1", "PC Admin2", "PC Admin3",
                    "Nota", "Tipo Conexión", "Zona", "Centro Costo"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for (Puntos p : puntos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(p.getCodigo());
                row.createCell(1).setCellValue(p.getNombre());
                row.createCell(2).setCellValue(p.getTecnologia());
                row.createCell(3).setCellValue(p.getObservacion());
                row.createCell(4).setCellValue(p.getIpRadio());
                row.createCell(5).setCellValue(p.getIpTelefono());
                row.createCell(6).setCellValue(p.getRaspberry());
                row.createCell(7).setCellValue(p.getRbetplay());
                row.createCell(8).setCellValue(p.getDvr());
                row.createCell(9).setCellValue(p.getPcVenta());
                row.createCell(10).setCellValue(p.getPcAdmin1());
                row.createCell(11).setCellValue(p.getPcAdmin2());
                row.createCell(12).setCellValue(p.getPcAdmin3());
                row.createCell(13).setCellValue(p.getNota());
                row.createCell(14).setCellValue(p.getTipoConexion().getName());
                row.createCell(15).setCellValue(p.getZona().getName());
                row.createCell(16).setCellValue(p.getCentroCosto().getName());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generando el Excel", e);
        }
    }







    @Override
    public byte[] generatePlainTextReportForWireless(List<Integer> centroCostoCodes, List<Integer> zonaCodes, List<Integer> tipoConexionCodes) {
        List<Puntos> puntos = puntosRepository.findAll().stream()
                .filter(p -> tipoConexionCodes == null || tipoConexionCodes.isEmpty() || (p.getTipoConexion() != null && tipoConexionCodes.contains(p.getTipoConexion().getCode())))
                .filter(p -> centroCostoCodes == null || centroCostoCodes.isEmpty() || (p.getCentroCosto() != null && centroCostoCodes.contains(p.getCentroCosto().getCode())))
                .filter(p -> zonaCodes == null || zonaCodes.isEmpty() || (p.getZona() != null && zonaCodes.contains(p.getZona().getCode())))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Puntos p : puntos) {
            String nombreFormateado = p.getNombre().trim().replaceAll("\\s+", "_").toUpperCase();
            String linea = String.format("%-60s ansible_ssh_host=%s", p.getCodigo() + "_" + nombreFormateado, p.getPcVenta());
            sb.append(linea).append(System.lineSeparator());
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }






    //Metodos Auxiliares para el servicio bulkInsertFromExcel

    @Override
    public Integer getTipoConexionCodeByName(String name) {
        return tipoConexionRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Tipo conexión no encontrado: " + name))
                .getCode();
    }

    @Override
    public Integer getZonaCodeByName(String name) {
        return zonaRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Zona no encontrada: " + name))
                .getCode();
    }

    @Override
    public Integer getCentroCostoCodeByName(String name) {
        return centroCostoRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Centro costo no encontrado: " + name))
                .getCode();
    }






    @Override
    public List<PointResponse> bulkInsertFromExcel(MultipartFile file) {
        List<PointResponse> inserted = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(0) == null) continue; // Fila vacía

                CreatePointRequest request = new CreatePointRequest();
                request.setCodigo((int) row.getCell(0).getNumericCellValue());
                request.setNombre(row.getCell(1).getStringCellValue());
                request.setTecnologia(row.getCell(2).getStringCellValue());
                request.setObservacion(row.getCell(3).getStringCellValue());
                request.setIpRadio(row.getCell(4).getStringCellValue());
                request.setIpTelefono(row.getCell(5).getStringCellValue());
                request.setRaspberry(row.getCell(6).getStringCellValue());
                request.setRbetplay(row.getCell(7).getStringCellValue());
                request.setDvr(row.getCell(8).getStringCellValue());
                request.setPcVenta(row.getCell(9).getStringCellValue());
                request.setPcAdmin1(row.getCell(10).getStringCellValue());
                request.setPcAdmin2(row.getCell(11).getStringCellValue());
                request.setPcAdmin3(row.getCell(12).getStringCellValue());
                request.setNota(row.getCell(13).getStringCellValue());

                String tipoConexionName = row.getCell(14).getStringCellValue();
                String zonaName = row.getCell(15).getStringCellValue();
                String centroCostoName = row.getCell(16).getStringCellValue();

                request.setTipoConexionCode(getTipoConexionCodeByName(tipoConexionName));
                request.setZonaCode(getZonaCodeByName(zonaName));
                request.setCentroCostoCode(getCentroCostoCodeByName(centroCostoName));

                inserted.add(create(request));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error procesando el archivo Excel", e);
        }

        return inserted;
    }

}

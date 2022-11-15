package com.grupo8.app.negocio;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.ReporteMesaDto;
import com.grupo8.app.dto.ReporteVentaDto;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.modelo.CierreComanda;
import com.grupo8.app.modelo.Empresa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GestionDeReportes {
  private Empresa empresa;

  public GestionDeReportes() {
    this.empresa = Empresa.getEmpresa();
  }

  /**
   * Genera una lista de reportes para cada mesa de la empresa ventas totales, cantidad de ventas y venta promedio
   * @return listado de reportes para cada mesa
   */
  public List<ReporteMesaDto> generarPromedioDeVentaPorMesa() {
    List<CierreComanda> cierres = empresa.getCierreComandas().getCierreComandas();
    List<ReporteMesaDto> resultado = new ArrayList<>();

    for (CierreComanda cierre : cierres) {
      Optional<ReporteMesaDto> yaExiste = resultado.stream().filter(r -> r.getNumeroMesa() == cierre.getMesa().getNroMesa()).findFirst();

      if (yaExiste.isPresent()) {
        ReporteMesaDto reporte = yaExiste.get();
        reporte.setCantidadVentas(reporte.getCantidadVentas() + 1);
        reporte.setVentasTotales(reporte.getVentasTotales() + cierre.getTotal());
        reporte.setVentaPromedio(reporte.getVentasTotales() / reporte.getCantidadVentas());
      } else {
        ReporteMesaDto reporte = new ReporteMesaDto();
        reporte.setNumeroMesa(cierre.getMesa().getNroMesa());
        reporte.setCantidadVentas(1);
        reporte.setVentasTotales(cierre.getTotal());
        reporte.setVentaPromedio(cierre.getTotal());
        resultado.add(reporte);
      }
    }

    return resultado;
  }

  /**
   * Genera una lista de reportes para cada mozo de la empresa ventas totales, cantidad de ventas y venta promedio
   * @return listado de reportes para cada mozo
   */
  private List<ReporteVentaDto> generarReporteDeVentas() {
    List<CierreComanda> cierres = empresa.getCierreComandas().getCierreComandas();
    List<ReporteVentaDto> resultado = new ArrayList<>();

    for (CierreComanda cierre : cierres) {
      MozoDTO mozo = MozoDTO.of(cierre.getMesa().getMozoAsignado());
      Optional<ReporteVentaDto> yaExiste = resultado.stream().filter(r -> r.getMozo().getId().equals(mozo.getId())).findFirst();

      if (yaExiste.isPresent()) { //si existe el mozo en el reporte lo actualizo
        ReporteVentaDto reporte = yaExiste.get();
        reporte.setTotal(reporte.getTotal() + cierre.getTotal());
        reporte.setCantVentas(reporte.getCantVentas() + 1);
        reporte.setPromedio(reporte.getTotal() / reporte.getCantVentas());
      } else { //sino lo agrego
        ReporteVentaDto reporte = new ReporteVentaDto();
        reporte.setMozo(mozo);
        reporte.setTotal(cierre.getTotal());
        reporte.setCantVentas(1);
        resultado.add(reporte);
      }
    }

    return resultado;
  }

  /**
   * Obtiene el item de reporte de ventas con mayor venta total
   * @return
   * @throws MalaSolicitudException
   */
  public ReporteVentaDto maximo() throws MalaSolicitudException {
    Optional<ReporteVentaDto> maximo = generarReporteDeVentas().stream().max(Comparator.comparing(ReporteVentaDto::getTotal));

    if (maximo.isPresent()) {
      return maximo.get();
    } else {
      throw new MalaSolicitudException("No hay reportes de ventas");
    }
  }

  /**
   * Obtiene el item de reporte de ventas con menor venta total
   * @return
   * @throws MalaSolicitudException
   */
  public ReporteVentaDto minimo() throws MalaSolicitudException {
    Optional<ReporteVentaDto> minimo = generarReporteDeVentas().stream().min(Comparator.comparing(ReporteVentaDto::getTotal));

    if (minimo.isPresent()) {
      return minimo.get();
    } else {
      throw new MalaSolicitudException("No hay reportes de ventas");
    }
  }

  /**
   * Obtiene el item de reporte de ventas de un mozo en particular
   * @return reporte de ventas del mozo
   * @throws EntidadNoEncontradaException si no existe el mozo
   */
  public ReporteVentaDto reporteDeEmpleado(MozoDTO mozo) throws EntidadNoEncontradaException {
    Optional<ReporteVentaDto> reporte = generarReporteDeVentas().stream().filter(r -> r.getMozo().getId().equals(mozo.getId())).findFirst();

    if (reporte.isPresent()) {
      return reporte.get();
    } else {
      throw new EntidadNoEncontradaException("No hay ventas para el mozo seleccionado");
    }
  }



}

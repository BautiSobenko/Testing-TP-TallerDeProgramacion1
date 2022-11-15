package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteMesaDto {
  private int numeroMesa;
  private float ventaPromedio;
  private float ventasTotales;
  private int cantidadVentas;


  @Override
  public String toString() {
    return "{" +
      " numeroMesa='" + getNumeroMesa() + "'" +
      ", ventaPromedio='" + getVentaPromedio() + "'" +
      ", ventasTotales='" + getVentasTotales() + "'" +
      ", cantidadVentas='" + getCantidadVentas() + "'" +
      "}";
  }

}

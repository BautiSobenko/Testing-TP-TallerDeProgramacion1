package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteVentaDto {
  private MozoDTO mozo;
  private Float total;
  private Float promedio;
  private Integer cantVentas;

  @Override
  public String toString() {
    return "Mozo: " + mozo.getNombreCompleto() + " - Total: " + total + " - Promedio: " + promedio + " - Cantidad de ventas: " + cantVentas;
  }
}

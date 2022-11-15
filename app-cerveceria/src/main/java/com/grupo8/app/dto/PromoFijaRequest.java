package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromoFijaRequest {
    private String nombre;
    private List<DayOfWeek> diasPromo;

    private String idProducto;
    private Boolean dosPorUno;
    private Boolean dtoPorCantidad;
    private Integer dtoPorCantMin;
    private Double dtoPorCantPrecioU;
    private boolean activa;

    public static PromoFijaRequest of(PromoFijaDTO promoFijaDTO) {
        PromoFijaRequest promoFijaRequest = new PromoFijaRequest();
        promoFijaRequest.setNombre(promoFijaDTO.getNombre());
        promoFijaRequest.setDiasPromo(promoFijaDTO.getDiasPromo());
        promoFijaRequest.setIdProducto(promoFijaDTO.getProducto().getId());
        promoFijaRequest.setDosPorUno(promoFijaDTO.getDosPorUno());
        promoFijaRequest.setDtoPorCantidad(promoFijaDTO.getDtoPorCant());
        promoFijaRequest.setDtoPorCantMin(promoFijaDTO.getDtoPorCantMin());
        promoFijaRequest.setDtoPorCantPrecioU(promoFijaDTO.getDtoPorCantPrecioU());
        promoFijaRequest.setActiva(promoFijaDTO.isActivo());
        return promoFijaRequest;
    }
}

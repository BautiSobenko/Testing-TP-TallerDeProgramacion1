package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromoTemporalRequest {
    private String nombre;
    List<DayOfWeek> diasPromo;

    private String formaPago;
    private int porcentajeDescuento;
    private boolean acumulable;
    private boolean activa;


    public static PromoTemporalRequest of(PromoTemporalDTO promoTemporalDTO) {
        PromoTemporalRequest promoTemporalRequest = new PromoTemporalRequest();
        promoTemporalRequest.setNombre(promoTemporalDTO.getNombre());
        promoTemporalRequest.setDiasPromo(promoTemporalDTO.getDiasPromo());
        promoTemporalRequest.setFormaPago(promoTemporalDTO.getFormaPago());
        promoTemporalRequest.setPorcentajeDescuento(promoTemporalDTO.getPorcentajeDescuento());
        promoTemporalRequest.setAcumulable(promoTemporalDTO.isAcumulable());
        promoTemporalRequest.setActiva(promoTemporalDTO.isActivo());
        return promoTemporalRequest;
    }
}

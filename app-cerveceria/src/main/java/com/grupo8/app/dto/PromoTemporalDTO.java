package com.grupo8.app.dto;

import com.grupo8.app.modelo.Promociones.PromocionTemporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromoTemporalDTO extends PromocionDTO {
    private String formaPago;
    private int porcentajeDescuento;
    private boolean acumulable;

    public static PromoTemporalDTO of(PromocionTemporal promo) {
        PromoTemporalDTO promoDTO = new PromoTemporalDTO();
        promoDTO.setNombre(promo.getNombre());
        promoDTO.setIdPromocion(promo.getIdPromocion());
        promoDTO.setActivo(promo.isActivo());
        promoDTO.setDiasPromo(promo.getDiasPromo());
        promoDTO.setFormaPago(promo.getFormaPago());
        promoDTO.setPorcentajeDescuento(promo.getPorcentajeDescuento());
        promoDTO.setAcumulable(promo.isAcumulable());
        return promoDTO;
    }

    @Override
    public String toString() {
        return super.toString()+"{" +
                "formaPago='" + formaPago + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", acumulable=" + acumulable +
                '}';
    }
}

package com.grupo8.app.dto;

import com.grupo8.app.modelo.Promociones.PromocionFija;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PromoFijaDTO extends PromocionDTO {
    private ProductoDTO producto;
    private Boolean dosPorUno;
    private Boolean dtoPorCant;
    private Integer dtoPorCantMin;
    private Double dtoPorCantPrecioU;


    public static PromoFijaDTO of(PromocionFija promo) {
        PromoFijaDTO promoDTO = new PromoFijaDTO();
        promoDTO.setProducto(ProductoDTO.of(promo.getProducto()));
        promoDTO.setDosPorUno(promo.getDosPorUno());
        promoDTO.setDtoPorCant(promo.getDtoPorCant());
        promoDTO.setDtoPorCantMin(promo.getDtoPorCantMin());
        promoDTO.setDtoPorCantPrecioU(promo.getDtoPorCantPrecioU());

        promoDTO.setNombre(promo.getNombre());
        promoDTO.setIdPromocion(promo.getIdPromocion());
        promoDTO.setActivo(promo.isActivo());
        promoDTO.setDiasPromo(promo.getDiasPromo());
        return promoDTO;
    }

    @Override
    public String toString() {
        return  super.toString()+"{" +
                "producto=" + producto +
                ", dosPorUno=" + dosPorUno +
                ", dtoPorCant=" + dtoPorCant +
                ", dtoPorCantMin=" + dtoPorCantMin +
                ", dtoPorCantPrecioU=" + dtoPorCantPrecioU +
                '}';
    }
}

package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromocionDTO {
    private String nombre;
    private String idPromocion;
    private boolean activo;
    private List<DayOfWeek> diasPromo;

    public PromocionDTO(String nombre, String idPromocion, boolean activo, List<DayOfWeek> diasPromo) {
        this.nombre = nombre;
        this.idPromocion = idPromocion;
        this.activo = activo;
        this.diasPromo = diasPromo;
    }

    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", activo=" + activo +
                ", diasPromo=" + diasPromo +
                '}';
    }
}

package com.grupo8.app.dto;

import com.grupo8.app.modelo.Mozo;
import com.grupo8.app.tipos.EstadoMozo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MozoDTO {

    private String nombreCompleto;
    private Date fechaNacimiento;
    private String id;
    private int cantidadHijos;
    private EstadoMozo estadoMozo;

    public static MozoDTO of(Mozo mozo) {
        MozoDTO mozoDTO = new MozoDTO();
        mozoDTO.setNombreCompleto(mozo.getNombreCompleto());
        mozoDTO.setId(mozo.getId());
        mozoDTO.setFechaNacimiento(mozo.getFechaNacimiento());
        mozoDTO.setCantidadHijos(mozo.getCantidadHijos());
        mozoDTO.setEstadoMozo(mozo.getEstadoMozo());
        return mozoDTO;
    }

    @Override
    public String toString() {
        return "{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", cantidadHijos=" + cantidadHijos +
                ", estadoMozo=" + estadoMozo +
                '}';
    }
}

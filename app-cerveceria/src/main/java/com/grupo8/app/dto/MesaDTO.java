package com.grupo8.app.dto;

import com.grupo8.app.modelo.Mesa;
import com.grupo8.app.tipos.EstadoMesa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MesaDTO {
    private Integer nroMesa;
    private Integer cantSillas;
    private EstadoMesa estadoMesa;
    private MozoDTO mozoAsignado;

    public static MesaDTO of(Mesa mesa) {
        MesaDTO mesaDTO = new MesaDTO();

        mesaDTO.setNroMesa(mesa.getNroMesa());
        mesaDTO.setCantSillas(mesa.getCantSillas());
        mesaDTO.setEstadoMesa(mesa.getEstadoMesa());

        if (mesa.getMozoAsignado() != null) {
            mesaDTO.setMozoAsignado(MozoDTO.of(mesa.getMozoAsignado()));
        }

        return mesaDTO;
    }

    @Override
    public String toString() {
        return "{" +
                "nroMesa=" + nroMesa +
                ", cantSillas=" + cantSillas +
                ", estadoMesa=" + estadoMesa +
                ", mozoAsignado= " + (mozoAsignado != null ? mozoAsignado.getNombreCompleto() : "NO") +
                '}';
    }
}

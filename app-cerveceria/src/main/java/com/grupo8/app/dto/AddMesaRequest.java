package com.grupo8.app.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Clase que representa la solicitud de agregar una mesa
 */

@Getter
@Setter
@NoArgsConstructor
public class AddMesaRequest {
    private Integer nroMesa;
    private Integer cantSillas;
    private MozoDTO mozoAsignado;
}

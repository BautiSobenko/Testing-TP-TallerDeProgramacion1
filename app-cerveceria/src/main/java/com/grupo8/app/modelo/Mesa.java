package com.grupo8.app.modelo;

import com.grupo8.app.tipos.EstadoMesa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Mesa implements Serializable {
    private Integer nroMesa;
    private Integer cantSillas;
    private EstadoMesa estadoMesa;
    private Mozo mozoAsignado;

    public Mesa(Integer nroMesa, Integer cantSillas) {
        this.nroMesa = nroMesa;
        this.cantSillas = cantSillas;
        this.estadoMesa = EstadoMesa.LIBRE;
    }
}

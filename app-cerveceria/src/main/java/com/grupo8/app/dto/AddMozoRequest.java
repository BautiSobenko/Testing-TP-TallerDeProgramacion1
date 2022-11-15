package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AddMozoRequest {
    private String nombreCompleto;
    @NonNull private Date fechaNacimiento;
    private int cantidadHijos;

    public AddMozoRequest(String nombreCompleto, Date fechaNacimiento, int cantidadHijos) {
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.cantidadHijos = cantidadHijos;
    }
}

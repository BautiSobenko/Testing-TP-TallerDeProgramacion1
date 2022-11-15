package com.grupo8.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddProductoRequest {
    private String nombre;
    private float precio;
    private float costo;
    private Integer stock;
}

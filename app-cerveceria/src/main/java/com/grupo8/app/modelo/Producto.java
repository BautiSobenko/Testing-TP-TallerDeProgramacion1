package com.grupo8.app.modelo;

import com.grupo8.app.dto.AddProductoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Producto implements Serializable {
    private String id;
    private String nombre;
    private float precio;
    private float costo;
    private Integer stock;

    public Producto(String nombre, float precio, float costo, Integer stock) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
    }

    public void update(AddProductoRequest info) {
        this.nombre = info.getNombre();
        this.precio = info.getPrecio();
        this.costo = info.getCosto();
        this.stock = info.getStock();
    }
}

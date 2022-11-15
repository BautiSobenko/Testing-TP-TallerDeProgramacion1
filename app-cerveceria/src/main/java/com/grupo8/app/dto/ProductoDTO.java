package com.grupo8.app.dto;


import com.grupo8.app.modelo.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {
    private String id;
    private String nombre;
    private Float precio;
    private Float costo;
    private Integer stock;


    public static ProductoDTO of(Producto producto) {

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setCosto(producto.getCosto());
        productoDTO.setStock(producto.getStock());

        return productoDTO;
    }

    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", costo=" + costo +
                ", stock=" + stock +
                '}';
    }
}

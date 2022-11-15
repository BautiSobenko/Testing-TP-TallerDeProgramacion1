package com.grupo8.app.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Pedido {
  private Producto producto;
  private String id;
  private int cantidad;
  private float subtotal;
  private Date timestamp;
  private boolean esPromo; //este campo se utiliza si el pedido forma parte de una promocion, se excluye de la cuenta final

  public Pedido(Producto producto, int cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
    this.subtotal = producto.getPrecio() * cantidad;
    this.timestamp = new Date();
    this.esPromo = false;
    this.id = UUID.randomUUID().toString();
  }
}

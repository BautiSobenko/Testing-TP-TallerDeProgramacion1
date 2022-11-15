package com.grupo8.app.modelo;

import com.grupo8.app.tipos.EstadoComanda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//Representa una comanda de una mesa
@Getter
@Setter
@NoArgsConstructor
public class Comanda implements Serializable {
  private String id;
  private List<Pedido> pedidos;
  private EstadoComanda estadoPedido;
  private Date apertura;
  private Date cierre;
  private Mesa mesa;


  public Comanda(Mesa mesa) {
    pedidos = new ArrayList<>();
    estadoPedido = EstadoComanda.ABIERTA;
    apertura = new Date();
    cierre = null;
    this.mesa = mesa;
    id = UUID.randomUUID().toString();
  }

  public void agregarPedido(Pedido pedido) {
    pedidos.add(pedido);
  }
}

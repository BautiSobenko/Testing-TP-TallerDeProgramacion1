package com.grupo8.app.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la solicitud de agregar un pedido,
 * con los datos de la comanda y el producto
 */
@Getter
@Setter
public class PedidoRequest {
    private String idProducto;
    private Integer cantidad;
    private String idComanda;
}

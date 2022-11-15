package com.grupo8.app.dto;

import com.grupo8.app.modelo.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {
    private ProductoDTO producto;
    private int cantidad;
    private float subtotal;
    private Date timestamp;
    private boolean esPromo;

    public static PedidoDTO of(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setProducto(ProductoDTO.of(pedido.getProducto()));
        pedidoDTO.setCantidad(pedido.getCantidad());
        pedidoDTO.setSubtotal(pedido.getSubtotal());
        pedidoDTO.setTimestamp(pedido.getTimestamp());
        pedidoDTO.setEsPromo(pedido.isEsPromo());

        return pedidoDTO;
    }

    @Override
    public String toString() {
        return "{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", timestamp=" + timestamp +
                ", esPromo=" + esPromo +
                '}';
    }
}

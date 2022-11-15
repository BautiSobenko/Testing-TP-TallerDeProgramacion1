package com.grupo8.app.controladores;

import com.grupo8.app.dto.ComandaDTO;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.vistas.VistaAgregarPedidoComanda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAgregarPedido implements ActionListener {
  private static ControladorAgregarPedido instancia = null;
  private final GestionDeMesas gestionDeMesas;
  private final VistaAgregarPedidoComanda vista;
  private final GestionDeProductos gestionDeProductos;

  private ControladorAgregarPedido() {
    gestionDeMesas = new GestionDeMesas();
    vista = new VistaAgregarPedidoComanda();
    vista.setActionListener(this);
    gestionDeProductos = new GestionDeProductos();
  }

  public static ControladorAgregarPedido get(boolean mostrar) {
    if (instancia == null) {
      instancia = new ControladorAgregarPedido();
    }

    if (mostrar) {
      instancia.vista.mostrar();
    }

    instancia.actualizarListas();

    return instancia;
  }

  private void actualizarListas() {
    vista.setListComandas(gestionDeMesas.obtenerComandas().toArray(new ComandaDTO[0]));
    vista.setListProductos(gestionDeProductos.obtenerProductos().toArray(new ProductoDTO[0]));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String comando = e.getActionCommand();
    switch (comando) {
      case "AGREGAR":
        try {
          gestionDeMesas.agregarPedidoAComanda(vista.getPedido());
          vista.mostrarMensaje("Pedido agregado a mesa");
          actualizarListas();
        } catch (Exception ex) {
          vista.mostrarMensaje(ex.getMessage());
        }
        break;
      case "VOLVER":
        ControladorIniciarTurno.getControladorIniciarTurno(true);
        this.vista.esconder();
        break;
    }

  }
}

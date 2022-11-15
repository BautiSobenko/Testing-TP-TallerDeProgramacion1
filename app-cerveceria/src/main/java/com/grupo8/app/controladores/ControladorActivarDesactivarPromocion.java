package com.grupo8.app.controladores;

import com.grupo8.app.dto.PromocionDTO;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.vistas.VistaActivarDesactivarPromo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorActivarDesactivarPromocion implements ActionListener {
  private GestionDePromos gestionDePromos;
  private VistaActivarDesactivarPromo vista;
  private static ControladorActivarDesactivarPromocion instancia = null;

  private ControladorActivarDesactivarPromocion() {
    this.gestionDePromos = new GestionDePromos();
    this.vista = new VistaActivarDesactivarPromo();
    this.vista.setActionListener(this);
  }

  public static ControladorActivarDesactivarPromocion get(boolean mostrar) {
    if (instancia == null) {
      instancia = new ControladorActivarDesactivarPromocion();
    }

    if (mostrar) {
      instancia.vista.mostrar();
    }

    instancia.vista.setListaPromociones(instancia.gestionDePromos.obtenerPromos().toArray(new PromocionDTO[0]));
    return instancia;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String comando = e.getActionCommand();

    switch (comando) {
      case "CAMBIAR":
        try {
          boolean result = gestionDePromos.activarDesactivarPromo(vista.getIdPromocionSeleccionada());
          vista.mostrarMensaje("Promocion " + (result ? "activada" : "desactivada"));
          vista.setListaPromociones(gestionDePromos.obtenerPromos().toArray(new PromocionDTO[0]));
        } catch (Exception ex) {
          vista.mostrarMensaje(ex.getMessage());
        }
        break;
      case "VOLVER":
        ControladorPromociones.getControladorPromociones(true);
        this.vista.esconder();
        break;
    }
  }
}

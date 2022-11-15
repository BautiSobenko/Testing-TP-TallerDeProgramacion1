package com.grupo8.app.controladores;

import com.grupo8.app.dto.PromocionDTO;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.vistas.VistaEliminarPromo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEliminarPromo implements ActionListener {
    private static ControladorEliminarPromo instancia = null;
    private VistaEliminarPromo vista;
    private GestionDePromos gestionDePromos;

    private ControladorEliminarPromo() {
        this.vista = new VistaEliminarPromo();
        this.vista.setActionListener(this);
        this.gestionDePromos = new GestionDePromos();
    }

    public static ControladorEliminarPromo getControlador(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorEliminarPromo();
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
            case "Eliminar":
                try {
                    gestionDePromos.eliminarPromo(vista.getIdPromocionSeleccionada());
                    vista.mostrarMensaje("Promoción eliminada correctamente");
                    instancia.vista.setListaPromociones(instancia.gestionDePromos.obtenerPromos().toArray(new PromocionDTO[0]));
                } catch (Exception exception) {
                    vista.mostrarMensaje(exception.getMessage());
                }
                break;
            case "Volver":
                ControladorPromociones.getControladorPromociones(true);
                this.vista.esconder();
                break;
        }
    }
}

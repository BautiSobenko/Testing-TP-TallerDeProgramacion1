package com.grupo8.app.controladores;

import com.grupo8.app.vistas.VistaNuevaPromo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNuevaPromo implements ActionListener {
    private static ControladorNuevaPromo instancia = null;
    private VistaNuevaPromo vista;

    private ControladorNuevaPromo() {
        this.vista = new VistaNuevaPromo();
        this.vista.setActionListener(this);
    }

    public static ControladorNuevaPromo getControlador(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorNuevaPromo();
        }

        if (mostrar) {
            instancia.vista.mostrar();
        }

        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "PROMO_PRODUCTO":
                ControladorNuevaPromoProducto.getControladorNuevaPromoProducto(true); // TODO
                this.vista.esconder();
                break;
             case "PROMO_MEDIO_PAGO":
                ControladorNuevaPromoPorMedioDePago.getControlador(true); // TODO
                this.vista.esconder();
                break;
            case "VOLVER":
                ControladorPromociones.getControladorPromociones(true);
                this.vista.esconder();
                break;
        }
    }
}

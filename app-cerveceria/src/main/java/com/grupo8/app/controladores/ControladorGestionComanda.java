package com.grupo8.app.controladores;

import com.grupo8.app.dto.ComandaDTO;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.vistas.VistaGestionComandas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorGestionComanda implements ActionListener {
    private static ControladorGestionComanda instancia = null;
    private final VistaGestionComandas vista;
    private final GestionDeMesas gestionMesas;

    public ControladorGestionComanda() {
        this.vista = new VistaGestionComandas();
        this.vista.setActionListener(this);
        this.gestionMesas = new GestionDeMesas();
    }

    public static ControladorGestionComanda getControladorGestionComanda(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorGestionComanda();
        }

        if (mostrar) {
            instancia.vista.mostrar();
        }
        instancia.vista.setListComandas(instancia.gestionMesas.obtenerComandas().toArray(ComandaDTO[]::new));
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Agregar":
                ControladorAgregarPedido.get(true);
                this.vista.esconder();
                break;
            case "Cerrar":
                ControladorCerrarComanda.getControladorCerrarComanda(true, this.vista.getComanda());
                this.vista.esconder();
                break;
            case "Atras":
                ControladorIniciarTurno.getControladorIniciarTurno(true);
                this.vista.esconder();
                break;
        }
    }
}

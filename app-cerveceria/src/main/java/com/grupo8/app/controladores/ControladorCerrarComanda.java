package com.grupo8.app.controladores;

import com.grupo8.app.dto.ComandaDTO;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.vistas.VistaCerrarComanda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCerrarComanda implements ActionListener {

    private final VistaCerrarComanda vista;
    private Empresa empresa;
    private static ControladorCerrarComanda instancia = null;
    private GestionDeMesas gestionMesas;
    private ComandaDTO comandaSeleccionada;



    public ControladorCerrarComanda() {
        this.vista = new VistaCerrarComanda();
        this.empresa = Empresa.getEmpresa();
        this.vista.setActionListener(this);
        this.gestionMesas = new GestionDeMesas();
    }

    public static ControladorCerrarComanda getControladorCerrarComanda(boolean mostrar, ComandaDTO comandaSeleccionada) {
        if (instancia == null) {
            instancia = new ControladorCerrarComanda();
        }
        if (mostrar) {
            instancia.vista.mostrar();
        }
        instancia.vista.setComanda(comandaSeleccionada);
        return instancia;
    }

    private void setComandaSeleccionada(ComandaDTO comandaSeleccionada) {
        this.comandaSeleccionada = comandaSeleccionada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "CerrarComanda":
                try {
                    gestionMesas.cerrarComanda(this.vista.getComanda().getId(), this.vista.getMedioPagoSeleccionado());
                    this.vista.mostrarMensaje("La comanda se cerro correctamente");
                } catch (EntidadNoEncontradaException ex) {
                    this.vista.mostrarMensaje(ex.getMessage());
                }

                this.vista.esconder();
                ControladorGestionComanda.getControladorGestionComanda(true);
                break;
            case "Atras":
                this.vista.esconder();
                ControladorGestionComanda.getControladorGestionComanda(true);
                break;
        }
    }

}

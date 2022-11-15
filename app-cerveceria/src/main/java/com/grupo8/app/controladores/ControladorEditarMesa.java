package com.grupo8.app.controladores;

import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaEditarMesa;
import com.grupo8.app.vistas.VistaEditarProducto;
import com.grupo8.app.vistas.VistaEliminarMesa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEditarMesa implements ActionListener {
    private VistaEditarMesa vista;
    private Empresa empresa;
    private static ControladorEditarMesa instancia = null;
    private GestionDeMesas gestionDeMesas;

    public ControladorEditarMesa() {
        this.vista = new VistaEditarMesa();
        this.empresa = Empresa.getEmpresa();
        this.vista.setActionListener(this);
        this.gestionDeMesas = new GestionDeMesas();
        this.vista.setListaAEditar(gestionDeMesas.obtenerMesas().toArray(new MesaDTO[0]));
    }

    public static ControladorEditarMesa get(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorEditarMesa();
        }
        if (mostrar) {
            instancia.vista.mostrar();
        }
        instancia.vista.setListaAEditar(instancia.gestionDeMesas.obtenerMesas().toArray(new MesaDTO[0]));
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "EDITAR":
                ControladorNuevaMesa.getControladorNuevaMesa(true,this.vista.obtenerNroMesa());
                this.vista.esconder();
                break;
            case "VOLVER":
                this.vista.esconder();
                ControladorMesa.getControladorMesa(true);
                break;
        }

    }
}

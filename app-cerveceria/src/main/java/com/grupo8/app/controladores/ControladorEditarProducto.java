package com.grupo8.app.controladores;

import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaEditarProducto;
import com.grupo8.app.vistas.VistaEliminarMesa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEditarProducto implements ActionListener {
    private VistaEditarProducto vista;
    private Empresa empresa;
    private static ControladorEditarProducto instancia = null;
    private GestionDeProductos gestionDeProductos;

    public ControladorEditarProducto() {
        this.vista = new VistaEditarProducto();
        this.empresa = Empresa.getEmpresa();
        this.vista.setActionListener(this);
        this.gestionDeProductos = new GestionDeProductos();
        this.vista.setListaAEditar(gestionDeProductos.obtenerProductos().toArray(new ProductoDTO[0]));
    }

    public static ControladorEditarProducto get(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorEditarProducto();
        }
        if (mostrar) {
            instancia.vista.mostrar();
        }
        instancia.vista.setListaAEditar(instancia.gestionDeProductos.obtenerProductos().toArray(new ProductoDTO[0]));
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "EDITAR":
                ControladorNuevoProducto.get(true, this.vista.obtenerIdProducto());
                this.vista.esconder();
                break;
            case "VOLVER":
                this.vista.esconder();
                ControladorProductos.getControladorProductos(true);
                break;
        }

    }
}

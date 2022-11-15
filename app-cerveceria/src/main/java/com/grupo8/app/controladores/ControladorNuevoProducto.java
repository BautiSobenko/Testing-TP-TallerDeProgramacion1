package com.grupo8.app.controladores;

import com.grupo8.app.modelo.Empresa;

import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.vistas.VistaNuevoProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNuevoProducto implements ActionListener {

    private VistaNuevoProducto vista;
 private static ControladorNuevoProducto instancia = null;
    private GestionDeProductos gestionDeProductos;

    public ControladorNuevoProducto() {
        this.vista = new VistaNuevoProducto(null);
        this.vista.setActionListener(this);
        this.gestionDeProductos = new GestionDeProductos();
    }

    public static ControladorNuevoProducto get(boolean mostrar, String id) {
        if (instancia == null) {
            instancia = new ControladorNuevoProducto();
        }
        if (mostrar) {
            instancia.vista.mostrar();
        }
        if (id != null) {
            try {
                instancia.vista.setProductoEditable(instancia.gestionDeProductos.obtenerPorId(id));
            } catch (Exception e) {
                instancia.vista.mostrarMensaje(e.getMessage());
            }
        } else {
            instancia.vista.resetearEditable(); //Prepara la vista por si llega un producto nuevo
        }
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (vista.getIdEditable() == null) { //Creacion de producto nuevo
            switch (comando) {
                case "ACEPTAR":
                    gestionDeProductos.addProducto(this.vista.getFormulario());
                    vista.mostrarMensaje("Producto creado correctamente");
                    break;
                case "VOLVER":
                    ControladorProductos.getControladorProductos(true);
                    vista.esconder();
                    break;
            }
        } else { //Edicion de producto
            switch (comando) {
                case "ACEPTAR":
                    try {
                        gestionDeProductos.editProducto(vista.getIdEditable(), vista.getFormulario());
                        vista.mostrarMensaje("Producto editado correctamente");
                        ControladorProductos.getControladorProductos(true);
                        this.vista.esconder();
                    } catch (Exception ex) {
                        vista.mostrarMensaje(ex.getMessage());
                    }
                    break;
                case "VOLVER":
                    ControladorEditarProducto.get(true);
                    vista.esconder();
                    break;
            }
        }
    }
}

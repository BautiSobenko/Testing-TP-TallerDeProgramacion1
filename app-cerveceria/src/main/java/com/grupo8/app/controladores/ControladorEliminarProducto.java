package com.grupo8.app.controladores;

import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.vistas.VistaEditarProducto;
import com.grupo8.app.vistas.VistaEliminarMesa;
import com.grupo8.app.vistas.VistaEliminarProducto;
import com.grupo8.app.vistas.VistaEliminarPromo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEliminarProducto implements ActionListener {
    private VistaEliminarProducto vista;
    private Empresa empresa;
    private static ControladorEliminarProducto instancia = null;
    private GestionDeProductos gestionDeProductos;

    public ControladorEliminarProducto() {
        this.vista = new VistaEliminarProducto();
        this.empresa = Empresa.getEmpresa();
        this.vista.setActionListener(this);
        this.gestionDeProductos = new GestionDeProductos();
    }

    public static ControladorEliminarProducto get(boolean mostrar) {
        if (instancia == null) {
            instancia = new ControladorEliminarProducto();
        }
        if (mostrar) {
            instancia.vista.mostrar();
        }
        instancia.vista.setListProductosElim(instancia.gestionDeProductos.obtenerProductos().toArray(new ProductoDTO[0]));
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Eliminar":
                this.gestionDeProductos.deleteProducto(vista.obtenerIdProducto());
                this.vista.setListProductosElim(gestionDeProductos.obtenerProductos().toArray(new ProductoDTO[0]));//actualiza la lista sin el eliminado
                this.vista.mostrarMensaje("Producto eliminado correctamente");
                break;
            case "Volver":
                this.vista.esconder();
                ControladorProductos.getControladorProductos(true);
                break;
        }

    }
}

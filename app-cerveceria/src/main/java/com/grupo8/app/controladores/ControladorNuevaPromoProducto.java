package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.dto.ProductoDTO;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.negocio.GestionDeProductos;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.vistas.VistaNuevaPromoProducto;



public class ControladorNuevaPromoProducto implements ActionListener {

	private static ControladorNuevaPromoProducto instancia = null;
	private VistaNuevaPromoProducto vista = null;
	private GestionDeProductos gestionDeProductos;
	private GestionDePromos gestionDePromos;

	private ControladorNuevaPromoProducto() {
		this.vista = new VistaNuevaPromoProducto();
		this.vista.setActionListener(this);
		gestionDeProductos = new GestionDeProductos();
		gestionDePromos = new GestionDePromos();
	}

	public static ControladorNuevaPromoProducto getControladorNuevaPromoProducto(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorNuevaPromoProducto();
		}

		if (mostrar) {
			instancia.vista.mostrar();
		}

		instancia.vista.setListaProductos(instancia.gestionDeProductos.obtenerProductos().toArray(ProductoDTO[]::new));

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Aceptar":
				try {
					gestionDePromos.agregarPromoFija(this.vista.getFormulario());
					this.vista.mostrarMensaje("La promo se creo con exito");
				} catch (MalaSolicitudException e1) {
					this.vista.mostrarMensaje(e1.getMessage());
				}
				this.vista.esconder();
				ControladorNuevaPromo.getControlador(true);
				break;
			case "Volver":
				ControladorNuevaPromo.getControlador(true);
				this.vista.esconder();
				break;
			
		}
	}
}

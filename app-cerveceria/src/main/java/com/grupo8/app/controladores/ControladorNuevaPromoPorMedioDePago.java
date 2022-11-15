package com.grupo8.app.controladores;

import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.negocio.GestionDePromos;
import com.grupo8.app.vistas.VistaNuevaPromo;
import com.grupo8.app.vistas.VistaNuevaPromoPorMedioDePago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNuevaPromoPorMedioDePago implements ActionListener {
	private static ControladorNuevaPromoPorMedioDePago instancia = null;
	private VistaNuevaPromoPorMedioDePago vista;
	private GestionDePromos gestionDePromos;

	private ControladorNuevaPromoPorMedioDePago() {
		this.vista = new VistaNuevaPromoPorMedioDePago();
		this.vista.setActionListener(this);
		this.gestionDePromos = new GestionDePromos();
	}

	public static ControladorNuevaPromoPorMedioDePago getControlador(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorNuevaPromoPorMedioDePago();
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
		case "Volver":
			ControladorNuevaPromo.getControlador(true);
			this.vista.esconder();
			break;
		case "Aceptar":
			try {
				gestionDePromos.agregarPromoTemporal(this.vista.getFormulario());
				this.vista.mensajeExito("La nueva promo se agrego con exito");
			} catch (MalaSolicitudException e1) {
				this.vista.mensajeError("Solicitud incorrecta");
			}

			ControladorNuevaPromo.getControlador(true);
			this.vista.esconder();
			break;

		}
	}
}
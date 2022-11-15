package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.vistas.VistaPromo;

public class ControladorPromociones implements ActionListener {

	private static ControladorPromociones instancia = null;
	private final VistaPromo vista;

	private ControladorPromociones() {
		this.vista = new VistaPromo();
		this.vista.setActionListener(this);
	}

	public static ControladorPromociones getControladorPromociones(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorPromociones();
		}

		if (mostrar) {
			instancia.vista.mostrar();
		}

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "NuevaPromocion":
			ControladorNuevaPromo.getControlador(true);
			this.vista.esconder();
			break;
		case "EditarPromocion":
			ControladorActivarDesactivarPromocion.get(true);
			this.vista.esconder();
			break;
		case "EliminarPromocion":
			ControladorEliminarPromo.getControlador(true);
			this.vista.esconder();
			break;
		case "Volver":
			ControladorSesionOperario.getControladorSesionOperario(true);
			this.vista.esconder();
			break;

		}
	}
}
package com.grupo8.app.controladores;

import com.grupo8.app.vistas.VistaSesionOperario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSesionOperario implements ActionListener {
	private static ControladorSesionOperario instancia = null;
	private VistaSesionOperario vista = null;

	private ControladorSesionOperario() {
		this.vista = new VistaSesionOperario();
		this.vista.setActionListener(this);
	}

	public static ControladorSesionOperario getControladorSesionOperario(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorSesionOperario();
		}

		if (mostrar) {
			instancia.vista.mostrar();
		}

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "IniciarTurno":
			ControladorIniciarTurno.getControladorIniciarTurno(true);
			this.vista.esconder();
			break;
		case "ABMMesas":
			ControladorMesa.getControladorMesa(true);
			this.vista.esconder();
			break;
		case "ABMProductos":
			ControladorProductos.getControladorProductos(true);
			this.vista.esconder();
			break;
		case "Promociones":
			ControladorPromociones.getControladorPromociones(true);
			this.vista.esconder();
			break;
		case "Estadisticas":
			ControladorReportes.getInstancia(true);
			vista.esconder();
			break;
		case "AgregarMozo":
			ControladorNuevoMozo.getControlador(true);
			this.vista.esconder();
			break;
		case "EliminarMozo":
			ControladorEliminarMozo.getControladorEliminarMozo(true);
			this.vista.esconder();
			break;
		case "CerrarSesion":
			ControladorLogin.getControladorLogin(true);
			vista.esconder();
			break;
		
		}
	}
}

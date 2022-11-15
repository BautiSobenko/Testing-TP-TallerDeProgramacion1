package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.excepciones.EstadoInvalidoException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.vistas.VistaIniciarTurno;

public class ControladorIniciarTurno implements ActionListener {

	private VistaIniciarTurno vista;
	private Empresa empresa;
	private static ControladorIniciarTurno instancia = null;
	private GestionDeMesas gestionMesas;

	public ControladorIniciarTurno() {
		this.vista = new VistaIniciarTurno();
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionMesas = new GestionDeMesas();
	}

	public static ControladorIniciarTurno getControladorIniciarTurno(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorIniciarTurno();
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
		case "AsistenciaMozos":
			this.vista.esconder();
		 	ControladorAsistenciaMozos.getControladorAsistencia(true);
			break;
		case "GestionarMesas":
			this.vista.esconder();
			ControladorMesa.getControladorMesa(true);
			break;
		case "GestionarComandas":
			this.vista.esconder();
			ControladorGestionComanda.getControladorGestionComanda(true);
			break;
		case "CrearComanda":
			this.vista.esconder();
		 	ControladorAgregarComanda.getControladorAgregarComanda(true);
			break;
		case "CerrarTurno":
			try {
				this.gestionMesas.cerrarTurno();
				vista.cerrarExitoso("El cierre de mesa se realizo correctamente",
						" El cierre de mesa se realizo correctamente");
			} catch (EstadoInvalidoException exception) {
				vista.cerrarFracaso("No se pudo cerrar la mesa", "No se pudo cerrar la mesa");
			}
			break;
		case "Volver":
			this.vista.esconder();
	        if (ControladorLogin.getControladorLogin(false).getLogueado().getUsername().equals("admin")) {
	            ControladorSesionAdmin.getControladorSesionAdmin(true);
	        } else {
	            ControladorSesionOperario.getControladorSesionOperario(true);
	        }

		}

	}

}

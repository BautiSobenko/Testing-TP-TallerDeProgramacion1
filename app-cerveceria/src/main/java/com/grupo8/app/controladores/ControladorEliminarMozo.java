package com.grupo8.app.controladores;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaEliminarMozo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEliminarMozo implements ActionListener {

	private VistaEliminarMozo vista;
	private Empresa empresa;
	private static ControladorEliminarMozo instancia = null;
	private GestionDeUsuarios gestionUsuarios;

	public ControladorEliminarMozo() {
		this.vista = new VistaEliminarMozo();
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionUsuarios = new GestionDeUsuarios();
	}

	public static ControladorEliminarMozo getControladorEliminarMozo(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorEliminarMozo();
		}
		if (mostrar) {
			instancia.vista.mostrar();
		}
		instancia.vista.setListaMozos(instancia.gestionUsuarios.obtenerMozos().toArray(new MozoDTO[0]));
		return instancia;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "Listo":
			this.gestionUsuarios.deleteMozo(this.vista.getMozo());
			this.vista.setListaMozos(gestionUsuarios.obtenerMozos().toArray(new MozoDTO[0]));
			this.vista.mensajeExito("mozo eliminado correctamente");
			break;
		case "VER_SUELDO":
			try {
				Float resultado = this.gestionUsuarios.calcularSueldoMozo(this.vista.getMozo());
				this.vista.mensajeExito("El sueldo de " + this.vista.getMozo().getNombreCompleto() + " es de: " + resultado);
			} catch (Exception e1) {
				this.vista.mensajeError(e1.getMessage());
			}
			break;
		case "Atras":
			this.vista.esconder();
			ControladorSesionAdmin.getControladorSesionAdmin(true);
			break;
		}

	}
}
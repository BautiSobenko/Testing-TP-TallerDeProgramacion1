package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.OperarioDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaEliminarOperario;

public class ControladorEliminarOperario implements ActionListener {

	private VistaEliminarOperario vista;
	private Empresa empresa;
	private static ControladorEliminarOperario instancia = null;
	private GestionDeUsuarios gestionUsuarios;

	public ControladorEliminarOperario() {
		this.vista = new VistaEliminarOperario();
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionUsuarios = new GestionDeUsuarios();
	}

	public static ControladorEliminarOperario getControladorEliminarOperario(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorEliminarOperario();
		}
		if (mostrar) {
			instancia.vista.mostrar();
		}
		instancia.vista.setListaOperarios(instancia.gestionUsuarios.obtenerOperarios().toArray(new OperarioDTO[0]));
		return instancia;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "Eliminar":
			this.gestionUsuarios.deleteOperario(this.vista.getOperario());
			this.vista.setListaOperarios(gestionUsuarios.obtenerOperarios().toArray(new OperarioDTO[0]));
			this.vista.mostrarMensaje("Operario eliminado correctamente");
			break;
		case "Atras":
			this.vista.esconder();
			ControladorSesionAdmin.getControladorSesionAdmin(true);
			break;
		}

	}
}

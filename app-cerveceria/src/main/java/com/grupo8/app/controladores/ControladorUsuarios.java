package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.excepciones.PermisoDenegadoException;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaNuevoUsuario;

public class ControladorUsuarios implements ActionListener {
	private static ControladorUsuarios instancia = null;
	private VistaNuevoUsuario vista = null;

	private ControladorUsuarios() {
		this.vista = new VistaNuevoUsuario();
		this.vista.setActionListener(this);
	}

	public static ControladorUsuarios getControladorUsuarios(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorUsuarios();
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
		case "Registrar":
			GestionDeUsuarios g= new GestionDeUsuarios();
			try {
				g.addOperario(this.vista.getFormulario());
				this.vista.mensajeExito("El operario se registro con exito");
			} catch (PermisoDenegadoException e1) {
				this.vista.mensajeError("Permiso denegado");
			}
			break;
		case "Atras":
			ControladorSesionAdmin.getControladorSesionAdmin(true);
			this.vista.esconder();
			break;
		}
	}
}
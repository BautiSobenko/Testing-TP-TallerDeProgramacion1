package com.grupo8.app.controladores;


import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaNuevoMozo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorNuevoMozo implements ActionListener {
	private static ControladorNuevoMozo instancia = null;
	private VistaNuevoMozo vista = null;
	

	private ControladorNuevoMozo() {
		this.vista = new VistaNuevoMozo();
		this.vista.setActionListener(this);
	}

	public static ControladorNuevoMozo getControlador(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorNuevoMozo();
		}

		if (mostrar) {
			instancia.vista.mostrar();
		}

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Listo":
			GestionDeUsuarios g= new GestionDeUsuarios();
			g.addMozo(this.vista.getFormulario());
			this.vista.mostrarMensaje("El mozo se registro con exito");
			this.vista.limpiaCampos();
			break;
		case "Atras":
			 ControladorSesionAdmin.getControladorSesionAdmin(true);
			 this.vista.esconder();
			 break;
		}
	}
}
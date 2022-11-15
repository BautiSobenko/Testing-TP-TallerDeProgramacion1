package com.grupo8.app.controladores;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaSesionAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSesionAdmin implements ActionListener {
	private static ControladorSesionAdmin instancia = null;
	private VistaSesionAdmin vista = null;

	private ControladorSesionAdmin() {
		this.vista = new VistaSesionAdmin();
		this.vista.setActionListener(this);
	}

	public static ControladorSesionAdmin getControladorSesionAdmin(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorSesionAdmin();
		}

		if (mostrar) {
			instancia.vista.mostrar();
		}

		instancia.vista.setTextFieldSueldo(Empresa.getEmpresa().getSueldoBase().toString());
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
		case "AgregarOperarios":
			ControladorUsuarios.getControladorUsuarios(true);
			vista.esconder();
			break;
		case "EliminarOperarios":
			ControladorEliminarOperario.getControladorEliminarOperario(true);
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
		case "ActualizarSueldo":
			try {
				Empresa.getEmpresa().setSueldoBase(Float.parseFloat(vista.getSueldo()));
				Empresa.getEmpresa().persistirSueldo();
				this.vista.success("Sueldo actualizado", "Exito");
			} catch (NumberFormatException e1) {
				this.vista.failure("Ingrese un numero valido", "Error");
			}
			break;
		case "CERRAR":
			ControladorLogin.getControladorLogin(true);
			GestionDeUsuarios g= new GestionDeUsuarios();
			g.logout();
			vista.esconder();
			break;
		}
	}
}

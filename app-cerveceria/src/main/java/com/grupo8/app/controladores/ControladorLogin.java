package com.grupo8.app.controladores;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Operario;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.ILogin;
import com.grupo8.app.vistas.VistaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {
	private ILogin vista;
	private Operario logueado;
	private static ControladorLogin instancia = null;
	private Empresa empresa;
	private GestionDeUsuarios gestionDeUsuarios;

	public ControladorLogin() {
		this.vista = new VistaLogin();
		this.logueado = null;
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionDeUsuarios = new GestionDeUsuarios();
	}

	public static ControladorLogin getControladorLogin(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorLogin();
		}
		if (mostrar) {
			instancia.vista.mostrar();
		}
		instancia.vista.limpiaCampos();
		//ver como deshabilitar el boton Entrar cuando vuelvo al Login
		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "LOGIN":
			try {
				this.logueado = gestionDeUsuarios.login(vista.getUsername(), vista.getContrasena());
			} catch (Exception exception) {
				vista.error("Error", "Usuario o contraseña incorrectos");
				break;
			}
			if (this.logueado != null) {
				if (logueado.getUsername().equals("admin")) {
					ControladorSesionAdmin.getControladorSesionAdmin(true);
					this.vista.esconder();
				} else {
					this.vista.esconder();
					ControladorSesionOperario.getControladorSesionOperario(true);
				}
			}
			break;
		case "SALIR":
			System.exit(0);
			break;
		}
	}

	public ILogin getVista() {
		return vista;
	}

	public Operario getLogueado() {
		return logueado;
	}

}

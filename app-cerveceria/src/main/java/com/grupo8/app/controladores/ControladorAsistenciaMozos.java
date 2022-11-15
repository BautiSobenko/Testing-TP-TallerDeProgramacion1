package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.tipos.EstadoMozo;
import com.grupo8.app.vistas.VistaAsistenciaMozos;

public class ControladorAsistenciaMozos implements ActionListener {
	private VistaAsistenciaMozos vista;
	private Empresa empresa;
	private static ControladorAsistenciaMozos instancia = null;
	private GestionDeUsuarios gestionDeUsuarios;

	public ControladorAsistenciaMozos() {
		this.vista = new VistaAsistenciaMozos();
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionDeUsuarios = new GestionDeUsuarios();
	}

	public static ControladorAsistenciaMozos getControladorAsistencia(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorAsistenciaMozos();
		}
		if (mostrar) {
			instancia.vista.mostrar();
		}

		instancia.vista.setListaMozos(instancia.gestionDeUsuarios.obtenerMozos().toArray(new MozoDTO[0]));

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "Atras":
			ControladorIniciarTurno.getControladorIniciarTurno(true);
			this.vista.esconder();
			break;
		case "Ausente":
			if (this.vista.getMozoSeleccionado() != null)
				try {
					gestionDeUsuarios.tomarAsistencia(this.vista.getMozoSeleccionado().getId(), EstadoMozo.AUSENTE);
					this.vista.mostrarMensaje(this.vista.getMozoSeleccionado().getNombreCompleto() + " esta ausente");
				} catch (Exception ex) {
					this.vista.mostrarMensaje(ex.getMessage());
				}
			break;
		case "Franco":
			if (this.vista.getMozoSeleccionado() != null)
				try {
					gestionDeUsuarios.tomarAsistencia(this.vista.getMozoSeleccionado().getId(), EstadoMozo.FRANCO);
					this.vista.mostrarMensaje(this.vista.getMozoSeleccionado().getNombreCompleto() + " esta de franco");
				} catch (Exception ex) {
					this.vista.mostrarMensaje(ex.getMessage());
				}
			break;
		case "Presente":
			if (this.vista.getMozoSeleccionado() != null)
				try {
					gestionDeUsuarios.tomarAsistencia(this.vista.getMozoSeleccionado().getId(), EstadoMozo.ACTIVO);
					this.vista.mostrarMensaje(this.vista.getMozoSeleccionado().getNombreCompleto() + " esta activo");
				} catch (Exception ex) {
					this.vista.mostrarMensaje(ex.getMessage());
				}
			break;
		}
		this.vista.setListaMozos(instancia.gestionDeUsuarios.obtenerMozos().toArray(new MozoDTO[0]));
	}

}

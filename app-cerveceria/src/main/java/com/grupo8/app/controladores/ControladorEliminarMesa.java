package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.vistas.VistaEliminarMesa;

public class ControladorEliminarMesa implements ActionListener {

	private VistaEliminarMesa vista;
	private Empresa empresa;
	private static ControladorEliminarMesa instancia = null;
	private GestionDeMesas gestionMesas;

	public ControladorEliminarMesa() {
		this.vista = new VistaEliminarMesa();
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionMesas = new GestionDeMesas();
		this.vista.setListaMesasElim(gestionMesas.obtenerMesas().toArray(new MesaDTO[0]));
	}

	public static ControladorEliminarMesa getControladorEliminarMesa(boolean mostrar) {
		if (instancia == null) {
			instancia = new ControladorEliminarMesa();
		}
		if (mostrar) {
			instancia.vista.mostrar();
		}
		instancia.vista.setListaMesasElim(instancia.gestionMesas.obtenerMesas().toArray(new MesaDTO[0]));
		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "ELIMINAR":
			this.gestionMesas.deleteMesa(this.vista.obtenerNroMesa());
			this.vista.setListaMesasElim(gestionMesas.obtenerMesas().toArray(new MesaDTO[0])); //actualiza la lista sin el eliminado
			this.vista.mostrarMensaje("Mesa eliminada correctamente");
			break;
		case "Volver":
			this.vista.esconder();
			ControladorMesa.getControladorMesa(true);
			break;
		}

	}
}

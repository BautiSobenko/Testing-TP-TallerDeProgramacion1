package com.grupo8.app.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.excepciones.NumeroMesaInvalidoException;
import com.grupo8.app.excepciones.PermisoDenegadoException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.negocio.GestionDeMesas;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaNuevaMesa;

public class ControladorNuevaMesa implements ActionListener {

	private VistaNuevaMesa vista;
	private Empresa empresa;
	private static ControladorNuevaMesa instancia = null;
	private GestionDeMesas gestionMesas;



	public ControladorNuevaMesa() {
		this.vista = new VistaNuevaMesa(null);
		this.empresa = Empresa.getEmpresa();
		this.vista.setActionListener(this);
		this.gestionMesas = new GestionDeMesas();
	}

	public static ControladorNuevaMesa getControladorNuevaMesa(boolean mostrar, Integer nroMesa) {
		if (instancia == null) {
			instancia = new ControladorNuevaMesa();
		}
		instancia.vista.setListaMozos(
				instancia.empresa.getMozos().getMozos().stream().map(MozoDTO::of).toArray(MozoDTO[]::new));
		if (mostrar) {
			instancia.vista.mostrar();
		}
		if (nroMesa != null) {
			try {
				instancia.vista.setMesaEditable(instancia.gestionMesas.obtenerPorNro(nroMesa));
			} catch (Exception e) {
				instancia.vista.mensajeError("nro de mesa no encontrado");
			}
		} else {
			instancia.vista.resetearEditable(); //Prepara la vista por si llega un producto nuevo
		}

		return instancia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (this.vista.getNroMesaEditable() == null) {//creacion de producto nuevo
			switch (comando) {
			case "Volver":
				ControladorMesa.getControladorMesa(true);
				this.vista.esconder();
				break;
			case "Aceptar":
				GestionDeMesas g = new GestionDeMesas();
				try {
					g.addMesa(this.vista.getFormulario());
					this.vista.mensajeExito("La mesa se registro con exito");
					this.vista.limpiaCampos();
				} catch (NumeroMesaInvalidoException | MalaSolicitudException e1) {
					this.vista.mensajeError(e1.getMessage());
				}

				this.vista.getFormulario();
				break;
			}
		}else {
			switch (comando) {
            case "Aceptar":
                try {
                    gestionMesas.editMesa( vista.getFormulario());
                    vista.mensajeExito("La mesa se edito correctamente");
                    ControladorMesa.getControladorMesa(true);
                    this.vista.esconder();
                } catch (Exception ex) {
                    vista.mensajeError(ex.getMessage());
                }
                break;
            case "Volver":
                ControladorEditarMesa.get(true);
                vista.esconder();
                break;
        }
			
		}

	}

}

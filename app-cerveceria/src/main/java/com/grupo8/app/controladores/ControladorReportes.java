package com.grupo8.app.controladores;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.ReporteMesaDto;
import com.grupo8.app.dto.ReporteVentaDto;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.negocio.GestionDeReportes;
import com.grupo8.app.negocio.GestionDeUsuarios;
import com.grupo8.app.vistas.VistaReporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorReportes implements ActionListener {
  private GestionDeReportes gestionDeReportes;
  private VistaReporte vistaReportes;
  private GestionDeUsuarios gestionDeUsuarios;
  private static ControladorReportes instancia;

  private ControladorReportes() {
    this.gestionDeReportes = new GestionDeReportes();
    this.vistaReportes = new VistaReporte();
    this.gestionDeUsuarios = new GestionDeUsuarios();
    this.vistaReportes.setActionListener(this);
  }

  public static ControladorReportes getInstancia(boolean mostrar) {
    if (instancia == null) {
      instancia = new ControladorReportes();
    }
    if (mostrar) {
      instancia.vistaReportes.mostrar();
    }

    instancia.cargarReportes();

    return instancia;
  }

  public void cargarReportes() {
    try {
      this.vistaReportes.setTxtMaximo(this.gestionDeReportes.maximo().toString());
    } catch (MalaSolicitudException e) {
      this.vistaReportes.setTxtMaximo("No hay ventas");
    }
    try {
      this.vistaReportes.setTxtMinimo(this.gestionDeReportes.minimo().toString());
    } catch (MalaSolicitudException e) {
      this.vistaReportes.setTxtMinimo("No hay ventas");
    }
    this.vistaReportes.setListMozos(this.gestionDeUsuarios.obtenerMozos().toArray(MozoDTO[]::new));
    this.vistaReportes.setListMesa(this.gestionDeReportes.generarPromedioDeVentaPorMesa().toArray(ReporteMesaDto[]::new));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String comando = e.getActionCommand();

    switch (comando) {
      case "VOLVER":
        if (ControladorLogin.getControladorLogin(false).getLogueado().getUsername().equals("admin")) {
            ControladorSesionAdmin.getControladorSesionAdmin(true);
        } else {
            ControladorIniciarTurno.getControladorIniciarTurno(true);
        }
        this.vistaReportes.esconder();
        break;
      case "VER":
        if (this.vistaReportes.getMozoSeleccionado() == null) {
          this.vistaReportes.mostrarMensaje("Debe seleccionar un mozo");
        } else {
          try {
            ReporteVentaDto reporte = this.gestionDeReportes.reporteDeEmpleado(this.vistaReportes.getMozoSeleccionado());
            this.vistaReportes.setTextIndividual(reporte.toString());
          } catch (EntidadNoEncontradaException ex) {
            this.vistaReportes.mostrarMensaje(ex.getMessage());
          }
        }
        break;
    }
  }
}

package com.grupo8.app.vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaIniciarTurno extends JFrame {

	private JPanel contentPane;
	private JButton btnCrearComanda;
	private JButton btnAsistenciaMozos;
	private JButton btnVolver;
	private JButton btnGestionarMesas;
	private JButton btnGestionarComandas;
	private ActionListener actionListener;

	public VistaIniciarTurno() {
		setTitle("Iniciar Turno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 506, 370);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(null);

		btnAsistenciaMozos = new JButton("Asistencia Mozos");
		btnAsistenciaMozos.setBounds(132, 18, 250, 45);
		btnAsistenciaMozos.setActionCommand("AsistenciaMozos");
		btnAsistenciaMozos.setPreferredSize(new Dimension(250, 45));
		btnAsistenciaMozos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.add(btnAsistenciaMozos);
		btnAsistenciaMozos.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);

		btnGestionarMesas = new JButton("Gestionar mesas");
		btnGestionarMesas.setBounds(133, 11, 243, 45);
		btnGestionarMesas.setActionCommand("GestionarMesas");
		btnGestionarMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_6.setLayout(null);
		btnGestionarMesas.setPreferredSize(new Dimension(250, 45));
		panel_6.add(btnGestionarMesas);
		btnGestionarMesas.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnGestionarComandas = new JButton("Gestionar comandas");
		btnGestionarComandas.setBounds(130, 11, 250, 45);
		panel_1.add(btnGestionarComandas);
		btnGestionarComandas.setPreferredSize(new Dimension(250, 45));
		btnGestionarComandas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestionarComandas.setActionCommand("GestionarComandas");

		JPanel panel_1_2_1 = new JPanel();
		panel.add(panel_1_2_1);
		panel_1_2_1.setLayout(null);
		
				btnCrearComanda = new JButton("Crear comanda");
				btnCrearComanda.setBounds(129, 11, 250, 45);
				panel_1_2_1.add(btnCrearComanda);
				btnCrearComanda.setActionCommand("CrearComanda");
				btnCrearComanda.setPreferredSize(new Dimension(250, 45));
				btnCrearComanda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
				JButton btnCerrarTurno = new JButton("Cerrar turno");
				panel_2.add(btnCerrarTurno);
				btnCerrarTurno.setPreferredSize(new Dimension(250, 45));
				btnCerrarTurno.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnCerrarTurno.setActionCommand("CerrarTurno");

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(5, 375, 506, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		btnVolver = new JButton("Volver");
		btnVolver.setPreferredSize(new Dimension(250, 45));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnVolver);
	}

	public void setActionListener(ActionListener actionListener) {
		this.btnVolver.addActionListener(actionListener);

		this.btnAsistenciaMozos.addActionListener(actionListener);
		this.btnGestionarMesas.addActionListener(actionListener);
		this.btnCrearComanda.addActionListener(actionListener);

		this.btnGestionarComandas.addActionListener(actionListener);
		this.actionListener=actionListener;
	}

	public void mostrar() {
		this.setVisible(true);

	}

	public void esconder() {
		this.setVisible(false);

	}

	public void cerrarExitoso(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(null, titulo, mensaje, JOptionPane.INFORMATION_MESSAGE);
	}

	public void cerrarFracaso(String error, String titulo) {
		JOptionPane.showMessageDialog(null, error, titulo, JOptionPane.ERROR_MESSAGE);
	}

}

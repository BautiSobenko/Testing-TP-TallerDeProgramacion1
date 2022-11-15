package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class VistaSesionOperario extends JFrame {

	private JPanel contentPane;
	private JButton btnABMProductos;
	private JButton btnIniciaTurno;
	private JButton btnPromociones;
	private JButton btnEstadisticas;
	private JButton btnCerrarSesion;
	private JButton btnAbmMesas;
	private ActionListener actionListener;
	private JButton btnAgregarMozo;

	private JButton btnEliminarMozo;

	public VistaSesionOperario() {
		setTitle("Sesion Operario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 506, 380);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_1);
		
		btnIniciaTurno = new JButton("Iniciar turno");
		btnIniciaTurno.setActionCommand("IniciarTurno");
		btnIniciaTurno.setPreferredSize(new Dimension(200, 35));
		btnIniciaTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.add(btnIniciaTurno);
		btnIniciaTurno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		btnAgregarMozo = new JButton("Agregar Mozo");
		btnAgregarMozo.setActionCommand("AgregarMozo");
		panel_6.add(btnAgregarMozo);
		btnAgregarMozo.setPreferredSize(new Dimension(200, 35));
		btnAgregarMozo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnEliminarMozo = new JButton("Eliminar mozo");
		btnEliminarMozo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEliminarMozo.setPreferredSize(new Dimension(200, 35));
		btnEliminarMozo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminarMozo.setActionCommand("EliminarMozo");
		panel_6.add(btnEliminarMozo);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		btnAbmMesas = new JButton("ABM mesas");
		panel_1.add(btnAbmMesas);
		btnAbmMesas.setActionCommand("ABMMesas");
		btnAbmMesas.setPreferredSize(new Dimension(200, 35));
		btnAbmMesas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnABMProductos = new JButton("ABM productos");
		btnABMProductos.setActionCommand("ABMProductos");
		btnABMProductos.setPreferredSize(new Dimension(200, 35));
		panel_1.add(btnABMProductos);
		btnABMProductos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_1_1 = new JPanel();
		panel.add(panel_1_1);
		
		btnPromociones = new JButton("Promociones");
		btnPromociones.setPreferredSize(new Dimension(200, 35));
		btnPromociones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1_1.add(btnPromociones);
		btnPromociones.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPromociones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_1_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1_2.getLayout();
		panel.add(panel_1_2);
		
		btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setPreferredSize(new Dimension(200, 35));
		panel_1_2.add(btnEstadisticas);
		btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(5, 385, 506, 35);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.setActionCommand("CerrarSesion");
		btnCerrarSesion.setPreferredSize(new Dimension(200, 35));
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnCerrarSesion);
	}

	
	public void mostrar() {
		this.setVisible(true);
	}

	
	public void esconder() {
		this.setVisible(false);
	}
	
	
	public void setActionListener(ActionListener actionListener) {
		this.actionListener=actionListener;
		this.btnCerrarSesion.addActionListener(actionListener);
		this.btnABMProductos.addActionListener(actionListener);
		this.btnPromociones.addActionListener(actionListener);
		this.btnIniciaTurno.addActionListener(actionListener);
		this.btnAbmMesas.addActionListener(actionListener);
		this.btnEstadisticas.addActionListener(actionListener);
		this.btnAgregarMozo.addActionListener(actionListener);
		this.btnEliminarMozo.addActionListener(actionListener);
	}

	

}

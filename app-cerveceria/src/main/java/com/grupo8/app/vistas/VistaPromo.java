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

public class VistaPromo extends JFrame {

	private JPanel contentPane;
	private JButton btnEliminarPromocion;
	private JButton btnNuevaPromocion;
	private JButton btnVolver;
	private JButton btnEditarPromocion;

	public VistaPromo() {
		setTitle("Gestion promociones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_1);
		
		btnNuevaPromocion = new JButton("Nueva promocion");
		btnNuevaPromocion.setActionCommand("NuevaPromocion");
		btnNuevaPromocion.setPreferredSize(new Dimension(250, 45));
		btnNuevaPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.add(btnNuevaPromocion);
		btnNuevaPromocion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		btnEditarPromocion = new JButton("Activar/Desactivar promocion");
		btnEditarPromocion.setActionCommand("EditarPromocion");
		btnEditarPromocion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarPromocion.setPreferredSize(new Dimension(250, 45));
		panel_6.add(btnEditarPromocion);
		btnEditarPromocion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		btnEliminarPromocion = new JButton("Eliminar promocion");
		btnEliminarPromocion.setActionCommand("EliminarPromocion");
		btnEliminarPromocion.setPreferredSize(new Dimension(250, 45));
		panel_1.add(btnEliminarPromocion);
		btnEliminarPromocion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		btnVolver = new JButton("Volver");
		btnVolver.setPreferredSize(new Dimension(250, 45));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnVolver);
	}

	public void setActionListener(ActionListener actionListener) {
		
		this.btnVolver.addActionListener(actionListener);
		this.btnEliminarPromocion.addActionListener(actionListener);
		this.btnNuevaPromocion.addActionListener(actionListener);
		this.btnEditarPromocion.addActionListener(actionListener);
		
	}
	public void mostrar() {
		this.setVisible(true);
		
	}

	public void esconder() {
		this.setVisible(false);
		
	}
	
}

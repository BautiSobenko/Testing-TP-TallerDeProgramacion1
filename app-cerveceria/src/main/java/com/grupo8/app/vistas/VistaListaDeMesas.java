package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.grupo8.app.dto.MesaDTO;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class VistaListaDeMesas extends JFrame {

	private JPanel contentPane;
	private JButton btnVolver;
	
	DefaultTableModel model = new DefaultTableModel();	
	private JList<MesaDTO> listaMesas;
	
	public VistaListaDeMesas() {
		setTitle("Lista de Mesas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		model.addColumn("Nombre");
		model.addColumn("Rubro");
		model.addColumn("Tipo de Persona");
		model.addColumn("Puntaje");
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 227, 89, 23);
		contentPane.add(btnVolver);
		
		listaMesas = new JList();
		listaMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaMesas.setBounds(36, 11, 353, 205);
		contentPane.add(listaMesas);
	}

	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}

	public void setActionListener(ActionListener actionListener) {
		this.btnVolver.addActionListener(actionListener);
	}
	
	public void mostrar() {
		this.setVisible(true);
		
	}

	public void esconder() {
		this.setVisible(false);
		
	}
}

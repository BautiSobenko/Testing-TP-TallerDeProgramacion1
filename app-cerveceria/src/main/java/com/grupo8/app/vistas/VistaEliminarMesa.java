package com.grupo8.app.vistas;

import com.grupo8.app.dto.MesaDTO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Font;
import java.awt.FlowLayout;

public class VistaEliminarMesa extends JFrame implements MouseListener {

	private JPanel General;
	private ActionListener actionListener;
	private int idMesa;
	private JButton btnVolver;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JList<MesaDTO> listaMesasElim;
	
	public VistaEliminarMesa() {
		setTitle("Eliminar Mesa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 665);
		General = new JPanel();
		General.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(General);
		General.setLayout(null);
		
	
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 592, 620, 29);
		General.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnVolver);
		
		 btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("ELIMINAR");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnEliminar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 625, 581);
		General.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la mesa a eliminar");
		lblNewLabel.setBounds(41, 27, 265, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 605, 442);
		panel.add(scrollPane);
		
		listaMesasElim = new JList<MesaDTO>();
		scrollPane.setViewportView(listaMesasElim);
		listaMesasElim.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 listaMesasElim.addMouseListener(this);
	     btnEliminar.setEnabled(false);
	}

	public void mostrar() {
		this.setVisible(true);
		
	}

	public void esconder() {
		this.setVisible(false);
		
	}

	
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
        this.btnVolver.addActionListener(actionListener);
        this.actionListener = actionListener;
        this.btnEliminar.addActionListener(actionListener);
	}

	public int obtenerNroMesa() {
		return this.listaMesasElim.getSelectedValue().getNroMesa();
	}

	public void setListaMesasElim(MesaDTO[] mesas) {
		this.listaMesasElim.setListData(mesas);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	


	public void success(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        if(this.listaMesasElim.getSelectedValue() != null)
             this.btnEliminar.setEnabled(true);
    }

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	
}

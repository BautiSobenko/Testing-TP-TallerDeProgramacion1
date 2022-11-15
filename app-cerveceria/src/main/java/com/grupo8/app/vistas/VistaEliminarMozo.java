package com.grupo8.app.vistas;

import com.grupo8.app.dto.MozoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaEliminarMozo extends JFrame implements MouseListener {

	private ActionListener actionListener;
	private JPanel contentPane;
	private JButton btnListo;
	private JButton btnAtras;
	private JList<MozoDTO> listaMozos;
	private JButton btnSueldo;
	
	public VistaEliminarMozo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione mozo a eliminar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(119, 11, 217, 19);
		contentPane.add(lblNewLabel);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtras.setBounds(0, 313, 230, 23);
		contentPane.add(btnAtras);
		
		btnListo = new JButton("Listo");
		btnListo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListo.setBounds(231, 313, 209, 23);
		contentPane.add(btnListo);
		
		btnSueldo = new JButton("Ver sueldo");
		btnSueldo.setActionCommand("VER_SUELDO");
		btnSueldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSueldo.setBounds(119, 279, 209, 23);
		btnSueldo.setEnabled(false);
		contentPane.add(btnSueldo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 430, 194);
		contentPane.add(scrollPane);
		
		listaMozos = new JList<MozoDTO>();
		scrollPane.setViewportView(listaMozos);
		
		listaMozos.addMouseListener(this);
        btnListo.setEnabled(false);

	}
	
	public void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void mensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);

	}

	public void mostrar() {
		this.setVisible(true);
		btnListo.setEnabled(false);

	}

	public void esconder() {
		this.setVisible(false);

	}
	
	public void setActionListener(ActionListener actionListener) {
		
		this.actionListener = actionListener;
		this.btnAtras.addActionListener(actionListener);
		this.btnListo.addActionListener(actionListener);
		this.btnSueldo.addActionListener(actionListener);
	
	}
	
	public void setListaMozos(MozoDTO[] mozos) {
		listaMozos.setListData(mozos);
	}

	public  MozoDTO getMozo() {
		return this.listaMozos.getSelectedValue();
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
		 if(this.listaMozos.getSelectedValue() != null) {
			 this.btnListo.setEnabled(true);
			 this.btnSueldo.setEnabled(true);
		 }
		
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

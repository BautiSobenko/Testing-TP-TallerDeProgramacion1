package com.grupo8.app.vistas;

import com.grupo8.app.dto.ProductoDTO;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;


public class VistaEliminarProducto extends JFrame implements MouseListener {

	private JPanel General;
	private ActionListener actionListener;
	private JButton btnVolver;
	private JList<ProductoDTO> listProductosElim;
	private JButton btnAceptar;

	
	public VistaEliminarProducto() {
		setTitle("Eliminar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 665);
		General = new JPanel();
		General.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(General);
		General.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 620, 587);
		General.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el producto a eliminar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(37, 11, 307, 77);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 600, 434);
		panel.add(scrollPane);
		
		listProductosElim = new JList<ProductoDTO>();
		scrollPane.setViewportView(listProductosElim);
		listProductosElim.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 592, 620, 29);
		General.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnVolver);
		
		btnAceptar = new JButton("Eliminar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnAceptar);
		btnAceptar.setEnabled(false);

		listProductosElim.addMouseListener(this);
		
		
	}

	
	public void mostrar() {
		this.setVisible(true);
		btnAceptar.setEnabled(false);
		
		
	}

	
	public void esconder() {
		this.setVisible(false);
	}

	public String obtenerIdProducto() {
		return listProductosElim.getSelectedValue().getId();
	}

	public void setListProductosElim(ProductoDTO[] productos) {
		listProductosElim.setListData(productos);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	
	public void setActionListener(ActionListener actionListener) {
		
        this.btnVolver.addActionListener(actionListener);
        this.btnAceptar.addActionListener(actionListener);
        this.actionListener = actionListener;
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
		this.btnAceptar.setEnabled( this.listProductosElim.getSelectedValue() != null);
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	
	

	
}

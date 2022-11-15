package com.grupo8.app.vistas;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.PromocionDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;


public class VistaEliminarPromo extends JFrame implements MouseListener{

	private JPanel General;
	private ActionListener actionListener;
	private JButton btnVolver;
	private JButton btnEliminar;
	private JList<PromocionDTO> listPromo;
	

	public VistaEliminarPromo() {
		setTitle("Eliminar promocion");
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
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(35, 17, 1, 1);
		panel.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("Clickee sobre la promocion a eliminar: ");
		lblNewLabel_5_1.setBounds(0, 0, 428, 171);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_13.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Clickee sobre la promocion a eliminar: ");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5_1_1.setBounds(46, 5, 347, 25);
		panel.add(lblNewLabel_5_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 66, 590, 488);
		panel.add(scrollPane);
		
		listPromo = new JList<>();
		scrollPane.setViewportView(listPromo);
		listPromo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 592, 620, 29);
		General.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnVolver);
		
	    btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnEliminar);
		
		btnEliminar.setEnabled(false);
		listPromo.addMouseListener(this);
	}

	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
        this.btnVolver.addActionListener(actionListener);
        this.btnEliminar.addActionListener(actionListener);
        this.actionListener = actionListener;
	}

	public  PromocionDTO getPromo() {
		return this.listPromo.getSelectedValue();
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.listPromo.getSelectedIndex() !=-1){
            this.btnEliminar.setEnabled(true);
		}
	}
	
	public void mostrar() {
		this.setVisible(true);
		this.btnEliminar.setEnabled(false);
		
	}

	public void esconder() {
		this.setVisible(false);
		
	}

	public void setListaPromociones(PromocionDTO[] promociones) {
		this.listPromo.setListData(promociones);
		this.btnEliminar.setEnabled(false);
		
	}

	public String getIdPromocionSeleccionada() {
		return this.listPromo.getSelectedValue().getIdPromocion();
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}

package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.grupo8.app.dto.AddMesaRequest;
import com.grupo8.app.dto.AddProductoRequest;
import com.grupo8.app.dto.MesaDTO;
import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.PromocionDTO;
import com.grupo8.app.tipos.EstadoMesa;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Font;
import java.awt.FlowLayout;

public class VistaNuevaMesa extends JFrame implements KeyListener,MouseListener {

	private JPanel General;
	private ActionListener actionListener;
	private JTextField textFieldCantSillas;
	private JTextField textFieldNumeroMesas;
	private JButton btnVolver;
	private JButton btnAceptar;
	private JList<MozoDTO> listaMozos;
	private MesaDTO mesaEditable=null;

	private JLabel lblNewLabel_5;

	public VistaNuevaMesa(MesaDTO mesaEditable) {
		setTitle("Nueva Mesa");
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

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 349, 64);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Ingrese cantidad de sillas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);

		textFieldCantSillas = new JTextField();
		panel_1.add(textFieldCantSillas);
		textFieldCantSillas.setColumns(5);
		textFieldCantSillas.addKeyListener(this);

		JPanel panel_11 = new JPanel();
		panel_11.setBounds(10, 122, 367, 64);
		panel.add(panel_11);
		panel_11.setLayout(null);

		lblNewLabel_5 = new JLabel("Ingrese numero de mesa ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(31, 1, 229, 25);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_11.add(lblNewLabel_5);

		textFieldNumeroMesas = new JTextField();
		textFieldNumeroMesas.setBounds(270, 7, 46, 20);
		textFieldNumeroMesas.setColumns(5);
		panel_11.add(textFieldNumeroMesas);
		textFieldNumeroMesas.addKeyListener(this);

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(20, 238, 600, 299);
		panel.add(panel_13);
		panel_13.setLayout(null);

		listaMozos = new JList<>();
		listaMozos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaMozos.setBounds(10, 11, 580, 277);
		panel_13.add(listaMozos);
		listaMozos.addMouseListener(this);

		JLabel lblNewLabel_5_1 = new JLabel("Asigne Mozo: ");
		lblNewLabel_5_1.setBounds(10, 197, 182, 30);
		panel.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 592, 620, 29);
		General.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnVolver);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(btnAceptar);
		btnAceptar.setEnabled(false);
		this.limpiaCampos();

	}

	public void setActionListener(ActionListener actionListener) {
		
		this.actionListener = actionListener;
		this.btnVolver.addActionListener(actionListener);
		this.btnAceptar.addActionListener(actionListener);
		

	}

	public void limpiaCampos() {
		this.listaMozos.clearSelection();
		this.textFieldCantSillas.setText("0");
		this.textFieldNumeroMesas.setText("0");
		this.btnAceptar.setEnabled(false);

	}

	public AddMesaRequest getFormulario() {
		AddMesaRequest request = new AddMesaRequest();
		request.setCantSillas(Integer.parseInt(this.textFieldCantSillas.getText()));
		request.setNroMesa(Integer.parseInt(this.textFieldNumeroMesas.getText()));
		request.setMozoAsignado(listaMozos.getSelectedValue());
		return request;
	}

	public void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void mensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);

	}

	public void mostrar() {
		this.setVisible(true);
		btnAceptar.setEnabled(false);

	}
	
	public Integer getNroMesaEditable() {
		if (this.mesaEditable != null) {
			return this.mesaEditable.getNroMesa();
		} else {
			return null;
		}
	}

	public void setMesaEditable(MesaDTO mesa){
		this.mesaEditable=mesa;
		this.textFieldNumeroMesas.setText((mesa.getNroMesa().toString()));
		this.textFieldCantSillas.setText(mesa.getCantSillas().toString());
		
		if(mesa!=null) {
			this.textFieldNumeroMesas.setEnabled(false);
			this.lblNewLabel_5.setEnabled(false);
		}
		
		
	}

	public void resetearEditable() {
		this.mesaEditable = null;
		this.textFieldCantSillas.setText("");
		this.textFieldNumeroMesas.setText("");
		this.btnAceptar.setEnabled(false);

	}


	public void esconder() {
		this.setVisible(false);


	}

	@Override
	public void keyTyped(KeyEvent e) {
		


	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.btnAceptar.setEnabled(textFieldCantSillas.getText().length() > 0
				&& textFieldNumeroMesas.getText().length() > 0 && this.listaMozos.getSelectedValue() != null);



	}

	public void setListaMozos(MozoDTO[] mozos) {
		listaMozos.setListData(mozos);
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
		this.btnAceptar.setEnabled(textFieldCantSillas.getText().length() > 0
				&& textFieldNumeroMesas.getText().length() > 0 && this.listaMozos.getSelectedValue() != null);
		
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

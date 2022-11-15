package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.grupo8.app.dto.MesaDTO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaSeleccionarMesaComanda extends JFrame implements MouseListener{

	private JPanel contentPane;
	
	private JButton btnConfirmar;
	private JButton btnAtras;
	private ActionListener actionListener;
	private JScrollPane scrollPane;
	private JList<MesaDTO> listaMesas;
	

	public VistaSeleccionarMesaComanda() {
		setTitle("Agregar Comanda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblSeleccioneLaMesa = new JLabel("Seleccione la mesa donde agregar la comanda");
		lblSeleccioneLaMesa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeleccioneLaMesa.setBounds(10, 11, 426, 25);
		panel.add(lblSeleccioneLaMesa);
		
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirmar.setActionCommand("CONFIRMAR");
		btnConfirmar.setBounds(292, 521, 276, 34);
		panel.add(btnConfirmar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setActionCommand("ATRAS");
		btnAtras.setBounds(10, 521, 282, 34);
		panel.add(btnAtras);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 504, 442);
		panel.add(scrollPane);
		
		listaMesas = new JList<MesaDTO>();
		scrollPane.setViewportView(listaMesas);
		listaMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.listaMesas.addMouseListener(this);
		this.btnConfirmar.setEnabled(false);
	}

	public void mostrar() {
        this.setVisible(true);
		this.btnConfirmar.setEnabled(false);

    }
	

	public MesaDTO getMesa() {
		return this.listaMesas.getSelectedValue();
	}

	public void setListaMesas(MesaDTO[] mesas) {
		this.listaMesas.setListData(mesas);
	}
	
    public void esconder() {
        this.setVisible(false);
    }

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.btnConfirmar.addActionListener(actionListener);
		this.btnAtras.addActionListener(actionListener);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
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
		if(this.listaMesas.getSelectedValue() != null)
			this.btnConfirmar.setEnabled(true);
		
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

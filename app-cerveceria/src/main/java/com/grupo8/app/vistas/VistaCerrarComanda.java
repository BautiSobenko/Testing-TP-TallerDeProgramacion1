package com.grupo8.app.vistas;

import com.grupo8.app.dto.ComandaDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaCerrarComanda extends JFrame implements MouseListener {

	private final JButton btnCerrarComanda;
	private final JButton btnAtras;
	private final JRadioButton rdbtnCdni;
	private JRadioButton rdbtnMP;
	private final JRadioButton rdbtnEfectivo;
	private JRadioButton rdbtnTarjeta;
	private ActionListener actionListener;
	private ComandaDTO comandaSeleccionada;
	private JTextField textFieldMonto;
	private ButtonGroup radioGroup;
	
	public VistaCerrarComanda() {
		setTitle("Cerrar comanda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 407);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	 btnCerrarComanda = new JButton("Cerrar comanda");
		btnCerrarComanda.setActionCommand("CerrarComanda");
		btnCerrarComanda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCerrarComanda.setBounds(302, 334, 144, 23);
		contentPane.add(btnCerrarComanda);
		
		 btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(10, 334, 120, 23);
		contentPane.add(btnAtras);
		
		rdbtnCdni = new JRadioButton("Cuenta DNI");
		rdbtnCdni.setActionCommand("CuentaDNI");
		rdbtnCdni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnCdni.setBounds(27, 226, 109, 23);
		contentPane.add(rdbtnCdni);
		
	 rdbtnMP = new JRadioButton("Mercado Pago");
		rdbtnMP.setActionCommand("MP");
		rdbtnMP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMP.setBounds(184, 226, 144, 23);
		contentPane.add(rdbtnMP);
		
		rdbtnTarjeta = new JRadioButton("Tarjeta");
		rdbtnTarjeta.setActionCommand("Tarjeta");
		rdbtnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnTarjeta.setBounds(184, 171, 109, 23);
		contentPane.add(rdbtnTarjeta);
		
		 rdbtnEfectivo = new JRadioButton("Efectivo");
		rdbtnEfectivo.setActionCommand("Efectivo");
		rdbtnEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnEfectivo.setBounds(27, 171, 109, 23);
		contentPane.add(rdbtnEfectivo);
		
		JLabel lblNewLabel = new JLabel("Elija el medio de pago ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 125, 144, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblElMontoA = new JLabel("El monto a pagar es : $  ");
		lblElMontoA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblElMontoA.setBounds(25, 71, 151, 23);
		contentPane.add(lblElMontoA);

		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(195, 74, 86, 20);
		contentPane.add(textFieldMonto);
		textFieldMonto.setColumns(10);
		
		radioGroup = new ButtonGroup();
        radioGroup.add(rdbtnTarjeta);
        radioGroup.add(rdbtnCdni);
        radioGroup.add(rdbtnEfectivo);
        radioGroup.add(rdbtnMP);

		this.btnCerrarComanda.setEnabled(false);
		this.textFieldMonto.setEnabled(false);

		 this.rdbtnCdni.addMouseListener(this);
		 this.rdbtnEfectivo.addMouseListener(this);
		 this.rdbtnMP.addMouseListener(this);
		 this.rdbtnTarjeta.addMouseListener(this);

	}

	public void mostrar() {
		this.setVisible(true);

	}

	public void esconder() {
		this.setVisible(false);
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void setComanda(ComandaDTO comanda) {
		this.comandaSeleccionada=comanda;
		if(this.comandaSeleccionada!=null){
			this.textFieldMonto.setText(String.valueOf(comandaSeleccionada.getSubtotal()));
		}
	}

	public ComandaDTO getComanda() {
		return this.comandaSeleccionada;
	}
	public void setActionListener(ActionListener ac) {
		this.actionListener=ac;
		this.rdbtnCdni.addActionListener(ac);
		this.rdbtnMP.addActionListener(ac);
		this.rdbtnEfectivo.addActionListener(ac);
		this.rdbtnTarjeta.addActionListener(ac);
		this.btnAtras.addActionListener(ac);
		this.btnCerrarComanda.addActionListener(ac);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.btnCerrarComanda.setEnabled(this.rdbtnTarjeta.isSelected() || this.rdbtnMP.isSelected() ||
				this.rdbtnEfectivo.isSelected() || this.rdbtnCdni.isSelected());

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public String getMedioPagoSeleccionado() {
		return this.radioGroup.getSelection().getActionCommand();
	}
}

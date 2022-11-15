package com.grupo8.app.vistas;

import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.ReporteMesaDto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaReporte extends JFrame implements MouseListener {

	private ActionListener actionListener;

	private JPanel contentPane;
	private JTextPane txtMinimo;
	private JTextPane txtMaximo;
	private JList<ReporteMesaDto> listMesa;
	private JList<MozoDTO> listMozos;
	private JButton btnVolver;
	private JButton btnVer;
	private JTextPane textIndividual;

	
	public VistaReporte() {
		setTitle("Reportes de Venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setActionCommand("VOLVER");
		btnVolver.setBounds(0, 594, 164, 27);
		contentPane.add(btnVolver);
		
		JLabel lblTitulo = new JLabel("Reportes de venta");
		lblTitulo.setBounds(288, 11, 164, 22);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblTitulo);
		
		JLabel lblMax = new JLabel("Mozo con mayores ventas");
		lblMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMax.setBounds(10, 56, 176, 39);
		contentPane.add(lblMax);
		
		JLabel lblMin = new JLabel("Mozo con menores ventas");
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMin.setBounds(607, 62, 185, 27);
		contentPane.add(lblMin);
		
		JLabel lblEstadisticasIndividuales = new JLabel("Estadisticas individuales:");
		lblEstadisticasIndividuales.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstadisticasIndividuales.setBounds(82, 334, 168, 23);
		contentPane.add(lblEstadisticasIndividuales);
		
		btnVer = new JButton("Ver");
		btnVer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVer.setActionCommand("VER");
		btnVer.setBounds(367, 448, 85, 39);
		contentPane.add(btnVer);
		btnVer.setEnabled(false);
		
		JLabel lblMesa = new JLabel("Estadisticas de mesas");
		lblMesa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMesa.setBounds(288, 68, 164, 14);
		contentPane.add(lblMesa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 129, 218);
		contentPane.add(scrollPane);
		
		txtMaximo = new JTextPane();
		scrollPane.setViewportView(txtMaximo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(182, 94, 392, 217);
		contentPane.add(scrollPane_1);
		
		listMesa = new JList<>();
		scrollPane_1.setViewportView(listMesa);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(616, 94, 117, 217);
		contentPane.add(scrollPane_2);
		
		txtMinimo = new JTextPane();
		scrollPane_2.setViewportView(txtMinimo);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 380, 307, 193);
		contentPane.add(scrollPane_3);
		
		listMozos = new JList<>();
		scrollPane_3.setViewportView(listMozos);
		listMozos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(482, 383, 271, 192);
		contentPane.add(scrollPane_4);
		
	    textIndividual = new JTextPane();
		scrollPane_4.setViewportView(textIndividual);
		listMozos.addMouseListener(this);
	}

	public void setActionListener(ActionListener actionListener) {
		this.btnVolver.addActionListener(actionListener);
		this.btnVer.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public void setListMesa(ReporteMesaDto[] mesas) {
		this.listMesa.setListData(mesas);
	}

	public void setListMozos(MozoDTO[] mozos) {
		this.listMozos.setListData(mozos);
	}

	public void setTxtMaximo(String txtMaximo) {
		this.txtMaximo.setText(txtMaximo);
	}

	public void setTxtMinimo(String txtMinimo) {
		this.txtMinimo.setText(txtMinimo);
	}

	public void setTextIndividual(String textIndividual) {
		this.textIndividual.setText(textIndividual);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (listMozos.getSelectedIndex() != -1) {
			btnVer.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void mostrar() {
		this.setVisible(true);

	}

	public void esconder() {
		this.setVisible(false);
	}

	public MozoDTO getMozoSeleccionado() {
		return this.listMozos.getSelectedValue();
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}

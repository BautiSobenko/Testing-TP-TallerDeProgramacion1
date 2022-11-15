package com.grupo8.app.vistas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import com.grupo8.app.dto.MozoDTO;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class VistaAsistenciaMozos extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JButton btnPresente;
	private JButton btnFranco ;
	private JButton btnAusente;
	private ActionListener actionListener;
	private JList<MozoDTO> listaMozos;
	private JButton btnAtras;
	
	public VistaAsistenciaMozos() {
		setTitle("Asistencia mozos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		

		JLabel lblNewJgoodiesTitle = new JLabel("Asistencia Mozos");
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewJgoodiesTitle.setBounds(10, 0, 145, 21);
		panel.add(lblNewJgoodiesTitle);
		
		btnPresente = new JButton("Presente");
		btnPresente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPresente.setBounds(401, 51, 100, 33);
		panel.add(btnPresente);
		
		btnFranco = new JButton("Franco");
		btnFranco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFranco.setBounds(401, 122, 100, 33);
		panel.add(btnFranco);
		
	 	btnAusente = new JButton("Ausente");
	 	btnAusente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAusente.setBounds(401, 201, 100, 33);
		panel.add(btnAusente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 36, 364, 230);
		panel.add(scrollPane);
		
		listaMozos = new JList<>();
		scrollPane.setViewportView(listaMozos);
		listaMozos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBounds(0, 309, 247, 33);
		panel.add(btnAtras);

		listaMozos.addMouseListener(this);
	}
	public void mostrar() {
        this.setVisible(true);
	}
	
	public void setActionListener(ActionListener actionListener) {
		
		this.actionListener = actionListener;
		this.btnPresente.addActionListener(actionListener);
		this.btnAusente.addActionListener(actionListener);
		this.btnFranco.addActionListener(actionListener);
		this.btnAtras.addActionListener(actionListener);
	
	}

	public MozoDTO getMozoSeleccionado() {
		return listaMozos.getSelectedValue();
	}


	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void esconder() {
        this.setVisible(false);
    }
	
	public void setListaMozos(MozoDTO[] mozos) {
		listaMozos.setListData(mozos);
	}
	
	public void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void mensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		btnAusente.setEnabled(listaMozos.getSelectedValue() != null);
		btnPresente.setEnabled(listaMozos.getSelectedValue() != null);
		btnFranco.setEnabled(listaMozos.getSelectedValue() != null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

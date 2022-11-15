package com.grupo8.app.vistas;


import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.grupo8.app.dto.ComandaDTO;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class VistaGestionComandas extends JFrame implements MouseListener {

	private JPanel contentPane;
	private ActionListener actionListener;
	private JButton btnCerrar;
	private JLabel lblNewLabel;
	private JButton btnAtras;
	private JScrollPane scrollPane;
	private JButton btnAgregarPedido;
	private JList<ComandaDTO> listComandas;

	public VistaGestionComandas() {
		setTitle("Gestion Comandas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 492, 432);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnCerrar = new JButton("Cerrar comanda");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCerrar.setBounds(166, 398, 136, 23);
		btnCerrar.setActionCommand("Cerrar");
		panel.add(btnCerrar);
		
		lblNewLabel = new JLabel("Gestion comandas");
		lblNewLabel.setBounds(151, 7, 176, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAtras.setBounds(54, 398, 70, 23);
		panel.add(btnAtras);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 49, 393, 309);
		panel.add(scrollPane);
		
		listComandas = new JList<>();
		scrollPane.setViewportView(listComandas);
		
		btnAgregarPedido = new JButton("Agregar pedido");
		btnAgregarPedido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregarPedido.setBounds(340, 398, 142, 23);
		btnAgregarPedido.setActionCommand("Agregar");
		panel.add(btnAgregarPedido);
		
		this.btnCerrar.setEnabled(false);
		this.listComandas.addMouseListener(this);

	}
	
	public ComandaDTO getComanda() {
		return this.listComandas.getSelectedValue();
	}
	
	public void mostrar() {
		this.setVisible(true);
	}

	public void setListComandas(ComandaDTO[] comandas) {
		this.listComandas.setListData(comandas);
	}


	public void esconder() {
        this.setVisible(false);
    }

	public void setActionListener(ActionListener ac) {
		this.btnAgregarPedido.addActionListener(ac);
		this.btnCerrar.addActionListener(ac);
		this.btnAtras.addActionListener(ac);
		this.actionListener=ac;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.listComandas.getSelectedValue()!=null)
			this.btnCerrar.setEnabled(true);

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
